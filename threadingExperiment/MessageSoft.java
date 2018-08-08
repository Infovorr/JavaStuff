/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.Scanner;

/**
 * The main program class.
 * @author Val
 */
public class MessageSoft {
    
    //An array representing user accounts for the database
    private final static Account[] ACCOUNTLIST = new Account[1000];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean runningOne = true;              //Is the main loop running
        int users;                              //The number of current users
        Messenger messenger = new Messenger();  //The messenger object
        Scanner input = new Scanner(System.in); //User input
        
        //Populate the array with objects representing user accounts
        for (int i = 1; i <= 1000; i++) {
            Account x = new Account(i);
            ACCOUNTLIST[i] = x;
        }
        
        //Main program loop
        while (runningOne) {
            System.out.println("Please enter the number of users active this "
                + "session (1-1000, 0 to abort): ");
        
            users = input.nextInt();
            
            //Create threads for the current users
            if (users < 1001 && users > 0) {
                ExecutorService fixedPool = Executors.newFixedThreadPool(users);
                for (int i = 0; i <= users; i++) {
                    fixedPool.submit(() -> {
                        portal(messenger);
                    });
                }
            }
            //Requesting too many or not enough users
            else if (users > 1000 || users < 0) {
                System.out.println("Invalid number");
            }
            //Otherwise, exit
            else {
                runningOne = false;
            }
        }
        
    }
    
    //Interface for potential users
    public static void portal(Messenger mess) {
        boolean isRunning = true;                   //Is the interface running
        Scanner input = new Scanner(System.in);     //User input
        int accountSelection;                       //Account selected
        int optionSelection;                        //Option selected
        Account selectedAccount;                    //The account, if acquired
        
        System.out.println("Please enter the number of the account you want to"
                + " access, or 0 to quit (1-1000):");
        accountSelection = input.nextInt();
        
        //Try to acquire the account and use it if acquired
        if (!ACCOUNTLIST[accountSelection].getAccount()) {
            selectedAccount = ACCOUNTLIST[accountSelection];
         
            isRunning = true;
            while (isRunning) {
                System.out.println("Please select one of the following "
                            + "options:\n1.\tSend messages\n2.\tRead messages\n"
                            + "0.\tExit");
                optionSelection = input.nextInt();
            
                switch(optionSelection) {
                    case 1:
                        mess.outgoing(selectedAccount);
                        break;
                    case 2:
                        mess.incoming(selectedAccount);
                        break;
                    case 0:
                        ACCOUNTLIST[accountSelection].releaseAccount();
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }
              }
            }
            //If the account is already in use, and thus locked
            else {
               System.out.println("Specified account is currently in use.");
            }
            //Exit the program
            if (accountSelection == 0) {
                isRunning = false;
            }
    }
    
}
