/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDB;

/**
 *
 * @author Val
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookAuthorQueries {

	private final String LOCATION = ""; 	//Enter the correct address, depending on the specific database being used (MySQL, Oracle, etc.)
	private final String NAME = ""; 		//Login for the database
	private final String PASS = ""; 		//Password for the database
	
	private Connection connection;				//Connection used for database access
	private PreparedStatement addAuthor;		//Adds author to database
	private PreparedStatement verifyAuthor;		//Verifies existence of author in database
	private PreparedStatement editAuthor;		//Edits existing author in database
	private PreparedStatement getAuthor;		//Gets author from database
	private PreparedStatement verifyBook;		//Verifies existance of book in database
	private PreparedStatement addBook;			//Adds book to database
	private PreparedStatement editBook;			//Edits existing book in database
	private PreparedStatement addAuthorAndBook;	//Adds author and book to database
	private PreparedStatement getID;			//Gets the authorid of a specified author
	private PreparedStatement checkISBN;		//Verifies existence of given ISBN
	private PreparedStatement findPair;			//Checks whether a particular authorid-isbn pair exists
		
	public BookAuthorQueries() {
		try {
			connection = DriverManager.getConnection(LOCATION, NAME, PASS);
			addAuthor = connection.prepareStatement("INSERT INTO AUTHOR (FIRSTNAME, LASTNAME) VALUES (?, ?)");
			verifyAuthor = connection.prepareStatement("SELECT COUNT(1) FROM AUTHOR WHERE FIRSTNAME = ? and LASTNAME = ?");
			editAuthor = connection.prepareStatement("UPDATE AUTHOR SET FIRSTNAME = ?, LASTNAME = ? WHERE AUTHORID = ?");
			getAuthor = connection.prepareStatement("SELECT * FROM AUTHOR WHERE FIRSTNAME = ?");
			addBook = connection.prepareStatement("INSERT INTO BOOK VALUES (?, ?, ?, ?)");
			verifyBook = connection.prepareStatement("SELECT COUNT(1) FROM BOOK WHERE ISBN = ? AND TITLE = ? AND EDITION = ? AND COPYRIGHT = ?");
			editBook = connection.prepareStatement("UPDATE BOOK SET ISBN = ?, TITLE = ?, EDITIONNUMBER = ?, COPYRIGHT = ? WHERE TITLE = ?");
			addAuthorAndBook = connection.prepareStatement("INSERT INTO BOOKAUTHOR VALUES (?, ?)");
			getID = connection.prepareStatement("SELECT AUTHORID FROM AUTHOR WHERE FIRSTNAME = ? AND LASTNAME = ?");
			checkISBN = connection.prepareStatement("SELECT COUNT(1) FROM BOOK WHERE ISBN = ?");
			findPair = connection.prepareStatement("SELECT COUNT(1) FROM BOOKAUTHOR WHERE ISBN = ? AND AUTHORID = ?");
		}
		catch (SQLException whoops) {
			whoops.printStackTrace();
			System.exit(1);
		}
	}
	
	//Takes the first and last names of an author and adds the author to the database
	public void insertAuthor(String first, String last) {
		ResultSet resultSetB;
		ResultSet resultSetC;
		
		//First check to see if the author is already in the database
		try {
			verifyAuthor.setString(1, first);
			verifyAuthor.setString(2, last);
			
			resultSetC = verifyAuthor.executeQuery();
			
			//If not, add the author to the database
			if (!resultSetC.isBeforeFirst()) {
				try {
					addAuthor.setString(1, first);
					addAuthor.setString(2, last);
			
					resultSetB = addAuthor.executeQuery();
                                }
				catch (SQLException whoops) {
					whoops.printStackTrace();
					disconnect();
				}
			}
			else {
				System.out.println("Specified author already exists.");
			}
		}
		catch (SQLException whoops) {
			whoops.printStackTrace();
			disconnect();
		}
	}
	
	//Take the name of a given author and a new name for them
	public void changeAuthor(String oFirst, String oLast, String first, String last) {
		ResultSet resultSetA;
		ResultSet resultSetB;
		
		//First check to see if the author is in the database
		try {
			verifyAuthor.setString(1, oFirst);
			verifyAuthor.setString(2, oLast);
			
			resultSetA = verifyAuthor.executeQuery();
			
			//If so, modify the author's name
			if (resultSetA.isBeforeFirst()) {
				try {
					editAuthor.setString(1, first);
					editAuthor.setString(2, last);
				
					resultSetB = editAuthor.executeQuery();
				}
				catch (SQLException whoops) {
					whoops.printStackTrace();
					disconnect();
				}
			}
			else {
				System.out.println("Specified author does not exist.");
			}
		}
		catch (SQLException whoops) {
			whoops.printStackTrace();
			disconnect();
		}
	}
	
	//Retrieve information on a specified author, using their first name
	public void retrieveAuthor (String first) {
		ResultSet resultSet;
		
		try {
			getAuthor.setString(1, first);
			
			resultSet = getAuthor.executeQuery();
			
			if (resultSet.isBeforeFirst()) {
				resultSet.next();
				System.out.println("Author ID: " + resultSet.getInt("AUTHORID"));
				System.out.println("First Name: " + resultSet.getString("FIRSTNAME"));
				System.out.println("Last Name: " + resultSet.getString("LASTNAME"));
			}
			else {
				System.out.println("No such author.");
			}
		}
		catch (SQLException whoops) {
			whoops.printStackTrace();
			disconnect();
		}
	}
		
	//Adds a book to the database, given the ISBN, title, edition, and copyright
	public void insertBook(int isbn, String title, int edition, int copyright) {
		ResultSet resultSetA;
		ResultSet resultSetB;
		ResultSet resultSetC;
		
		//First check to see if the book is already in the database
		try {
			verifyBook.setInt(1, isbn);
			verifyBook.setString(2, title);
			verifyBook.setInt(3, edition);
			verifyBook.setInt(4, copyright);
			checkISBN.setInt(1, isbn);
			
			resultSetA = verifyBook.executeQuery();
			resultSetC = checkISBN.executeQuery();
			
			//If not, add it
			if (!resultSetA.isBeforeFirst() && !resultSetC.isBeforeFirst()) {
				try {
					addBook.setInt(1, isbn);
					addBook.setString(2, title);
					addBook.setInt(3, edition);
					addBook.setInt(4, copyright);
					
					resultSetB = addBook.executeQuery();
				}
				catch (SQLException whoops) {
					whoops.printStackTrace();
					disconnect();
				}
			}
			else if (!resultSetA.isBeforeFirst() && resultSetC.isBeforeFirst()) {
				System.out.println("Specified ISBN has already been assigned.");
			}
			else if (resultSetA.isBeforeFirst() && !resultSetC.isBeforeFirst()) {
				System.out.println("Specified book already exists.");
			}
			else {
				System.out.println("Specified book already exists with specified ISBN.");
			}
		}
		catch (SQLException whoops) {
			whoops.printStackTrace();
			disconnect();
		}
	}
		
	//Modified an existing book, given its old details and new ones
	public void changeBook(int oisbn, String otitle, int oedition, int ocopyright, int isbn, String title, int edition, int copyright) {
		ResultSet resultSetA;
		ResultSet resultSetB;
		ResultSet resultSetC;
		Boolean goodISBN;
		
		//First check to see if the book exists, and whether the new ISBN (if any) is already in use
		try {
			verifyBook.setInt(1, oisbn);
			verifyBook.setString(2, otitle);
			verifyBook.setInt(3, oedition);
			verifyBook.setInt(4, ocopyright);
			checkISBN.setInt(1, isbn);
			
			resultSetA = verifyBook.executeQuery();
			resultSetC = checkISBN.executeQuery();
			
			goodISBN = !resultSetC.isBeforeFirst() || (oisbn == isbn);
			
			//If everything checks out, make the changes
			if (resultSetA.isBeforeFirst() && goodISBN) {
				try {
					editBook.setInt(1, isbn);
					editBook.setString(2, title);
					editBook.setInt(3, edition);
					editBook.setInt(4, copyright);
					
					resultSetB = editBook.executeQuery();
				}
				catch (SQLException whoops) {
					whoops.printStackTrace();
					disconnect();
				}
			}
			else if (resultSetA.isBeforeFirst() && !goodISBN) {
				System.out.println("Specified ISBN already in use.");
			}
			else if (!resultSetA.isBeforeFirst() && goodISBN) {
				System.out.println("Book does not exist.");
			}
			else {
				System.out.println("Book does not exist, and specified ISBN already in use.");
			}
		}
		catch (SQLException whoops) {
			whoops.printStackTrace();
			disconnect();
		}
	}
	
	//Adds a book's ISBN && author's ID to the book-author table as a pair
	public void insertAuthorAndBook(int isbn, String first, String last) {
		ResultSet isbnExists;
		ResultSet IDfound;
		ResultSet pairExists;
		ResultSet addPair;
		int ID;
		
		//First check whether the ISBN and authorid exist
		try {
			getID.setString(1, first);
			getID.setString(2, last);
			checkISBN.setInt(1, isbn);
			
			isbnExists = checkISBN.executeQuery();
			IDfound = getID.executeQuery();
			
			//Then check whether the pair already exists in the table
			if (isbnExists.isBeforeFirst() && IDfound.isBeforeFirst()) {
				try {
					IDfound.next();
					ID = IDfound.getInt("AUTHORID");
					findPair.setInt(1, isbn);
					findPair.setInt(2, ID);
					
					pairExists = findPair.executeQuery();
					
					if (!pairExists.isBeforeFirst()) {
						addAuthorAndBook.setInt(1, ID);
						addAuthorAndBook.setInt(2, isbn);
						
						addPair = addAuthorAndBook.executeQuery();
					}
					else {
						System.out.println("Author and book are already in the book-author table.");
					}
				}
				catch (SQLException whoops) {
					whoops.printStackTrace();
					disconnect();
				}
			}
			else if (!isbnExists.isBeforeFirst() && IDfound.isBeforeFirst()) {
				System.out.println("Specified ISBN does not exist.");
			}
			else if (isbnExists.isBeforeFirst() && !IDfound.isBeforeFirst()) {
				System.out.println("Specified author does not exist.");
			}
			else {
				System.out.println("Specified ISBN and author do not exist.");
			}
		}
		catch (SQLException whoops) {
			whoops.printStackTrace();
			disconnect();
		}
	}
	
	public void disconnect() {
		try {
			connection.close();
		}
		catch (SQLException whoops) {
			whoops.printStackTrace();
		}
	}
}
