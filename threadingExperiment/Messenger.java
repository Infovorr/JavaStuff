/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * This class handles the database connection, all relevant queries, and user
 * input for the database queries and operations.
 * @author Val
 */
public class Messenger {
    
    private final String LOCATION = "";                    //Address of database
    private final String NAME = "";                        //Username for DB
    private final String PASS = "";                        //Password for DB
    private Connection connection;                         //Connection object
    private PreparedStatement countMsgs;
    private PreparedStatement sendMsg;
    private PreparedStatement readMsg;
    private PreparedStatement showMsgs;
    private PreparedStatement msgMax;
    private PreparedStatement tmsRead;
    private PreparedStatement updateM;
    private final Object readerLock = new Object();        //Access lock
    private final Object writerLock = new Object();        //Access lock
    
    public Messenger() {
        try {
            connection = DriverManager.getConnection(LOCATION, NAME, PASS);
            countMsgs = connection.prepareStatement("SELECT COUNT(*) FROM "
                    + "MESSAGE-DB");
            msgMax = connection.prepareStatement("SELECT MAX(MESSAGE-ID) + 1 "
                    + "FROM MESSAGE-DB WHERE RECEIVER-ID = ?");
            sendMsg = connection.prepareStatement("INSERT INTO MESSAGE-DB "
                    + "VALUES (?, ?, SYSDATE, ?, 0)");
            readMsg = connection.prepareStatement("SELECT MESSAGE FROM "
                    + "MESSAGE-DB WHERE RECEIVER-ID = ? AND MESSAGE-ID = ?");
            showMsgs = connection.prepareStatement("SELECT MESSAGE-ID, "
                    + "DATE-SENT, STATUS FROM MESSAGE-DB WHERE "
                    + "RECEIVER-ID = ?");
            tmsRead = connection.prepareStatement("SELECT STATUS FROM "
                    + "MESSAGE-DB WHERE RECEIVER-ID = ? AND MESSAGE-ID = ?");
            updateM = connection.prepareStatement("UPDATE MESSAGE-DB SET "
                    + "STATUS = ? WHERE RECEIVER-ID = ? AND MESSAGE-ID = ?");
        }
        catch (SQLException whoops) {
            whoops.printStackTrace();
            System.exit(1);
        }
    }
    
    //For sending messages
    public void outgoing(Account chosen) {
        Account acct = chosen;
        boolean isRunning = true;
        int selection;
        
        //Only one user can send at a time
        synchronized(writerLock) {
            Scanner input = new Scanner(System.in);
            while(isRunning) {
                System.out.println("Press 1 to send a message, or 2 to return "
                        + "to the previous menu.");
                selection = input.nextInt();
                switch(selection) {
                    case 1:
                        sendMessage(acct);
                        break;
                    case 2:
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            }
        }
    }
    
    //For reading messages
    public void incoming(Account chosen) {
        Account acct = chosen;
        boolean isRunning = true;
        int selection;
        
        //Only one user can read at a time
        synchronized(readerLock) {
            Scanner input = new Scanner(System.in);
            while(isRunning) {
                listMessages(acct);
                System.out.println("Please select the message you would like to"
                        + " read, or enter 1001 to exit: ");
                selection = input.nextInt();
                if (selection < 1001 && selection > 0) {
                    readMessage(acct, selection);
                }
                else if (selection == 1001) {
                    isRunning = false;
                }
                else {
                    System.out.println("Invalid option.");
                }
            }
        }
    }
    
    //List the available messages for a given user
    public void listMessages(Account chosen) {
        Account acct = chosen;
        ResultSet listed;
        
        try {
            showMsgs.setInt(1, acct.getNum());
            listed = showMsgs.executeQuery();
            
            if (listed.isBeforeFirst()) {
                System.out.println("Message ID\tTimes Read");
                while (listed.next()) {
                    System.out.println(listed.getInt("MESSAGE-ID") + "\t" + 
                            listed.getInt("STATUS"));
                }
            }
            else {
                System.out.println("No messages available.");
            }
        }
        catch (SQLException whoops) {
            whoops.printStackTrace();
            System.exit(1);
        }
    }
    
    //Read the specified message
    public void readMessage(Account chosen, int msg) {
        Account acct = chosen;
        int messageNum = msg;
        int readNum;
        ResultSet read;
        ResultSet timesRead;
        ResultSet update;
        String message;
        
        try {
            readMsg.setInt(1, acct.getNum());
            readMsg.setInt(2, messageNum);
            read = readMsg.executeQuery();
            
            if (read.isBeforeFirst()) {
                message = read.getString(1);
                System.out.println("Message follows: \n" + message);
            }
            
            //Find the number of times the message has been read
            tmsRead.setInt(1, acct.getNum());
            tmsRead.setInt(2, messageNum);
            timesRead = tmsRead.executeQuery();
            readNum = timesRead.getInt(1);
            
            //Increment and update the number of times the message has been read
            updateM.setInt(1, readNum);
            updateM.setInt(2, messageNum);
            updateM.setInt(3, acct.getNum());
            update = updateM.executeQuery();
        }
        catch (SQLException whoops) {
            whoops.printStackTrace();
            System.exit(1);
        }
    }
    
    //Send a message
    public void sendMessage(Account chosen) {
        Account acct = chosen;
        int target;
        ResultSet sent;
        Scanner input = new Scanner(System.in);
        String message;
        
        System.out.println("Please enter the user you want to message: ");
        target = input.nextInt();
        
        //Must be a valid account number
        if (target == acct.getNum() || target < 1 || target > 1000) {
            System.out.println("Invalid account.");
        }
        //Must have space available in the database
        else if (countMessages() > 999) {
            System.out.println("No more room in the database.");
        }
        else {
            System.out.println("Please enter the message you would like to "
                    + "send (Press enter twice to end the message): ");
            input.useDelimiter("\n\n");
            message = input.next();
            
            try {
                sendMsg.setInt(1, numMessages(acct) + 1);
                sendMsg.setString(2, message);
                sendMsg.setInt(3, acct.getNum());
                sent = sendMsg.executeQuery();
            }
            catch (SQLException whoops) {
                whoops.printStackTrace();
                System.exit(1);
            }
        }
    }
    
    //Count the number of messages in the database
    public int countMessages() {
        ResultSet count;
        
        try {
            count = countMsgs.executeQuery();
            return count.getInt(1);
        }
        catch (SQLException whoops) {
            whoops.printStackTrace();
            System.exit(1);
            return -1;
	}
    }
    
    //Calculate the highest message-id value in the user's inbox
    public int numMessages(Account chosen) {
        Account acct = chosen;
        ResultSet num;
        
        try {
            msgMax.setInt(1, acct.getNum());
            num = msgMax.executeQuery();
            return num.getInt(1);
        }
        catch (SQLException whoops) {
            whoops.printStackTrace();
            System.exit(1);
            return -1;
        }
    }
}
