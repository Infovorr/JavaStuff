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
 
import java.util.Scanner;


public class BookAuthorManipulation {
	
	public static void main(String args[]) {
		
		BookAuthorQueries queryEngine = new BookAuthorQueries();
		int option;													//Menu options
		Boolean running = true;											//Is the program still running?
		Scanner input = new Scanner(System.in);
		
		while (running) {
			displayMenu();
			option = input.nextInt();
			
			switch (option) {
				case 1: addWriter(queryEngine);
						break;
				case 2: modWriter(queryEngine);
						break;
				case 3: getWriter(queryEngine);
						break;
				case 4: addTitle(queryEngine);
						break;
				case 5: modTitle(queryEngine);
						break;
				case 6: addWriterAndTitle(queryEngine);
						break;
				case 7: running = false;
						break;
				default: System.out.println("Invalid option.");
						break;
			}
                }
	}
	
	//Generates the option menu for the program
	public static void displayMenu() {
		System.out.println("You have the following options: ");
		System.out.println("1 - Add a new author");
		System.out.println("2 - Edit an existing author");
		System.out.println("3 - Retrieve an author");
		System.out.println("4 - Add a new book");
		System.out.println("5 - Edit an existing book");
		System.out.println("6 - Add a new book and its author to the author-book table");
		System.out.println("7 - Quit");
		System.out.println("\nPlease enter the number that corresponds with your selection: ");
	}
	
	//Gathers necessary variables before calling the insertAuthor method
	public static void addWriter(BookAuthorQueries thing) {
                Scanner input = new Scanner(System.in);
		System.out.println("Please enter the first name of the author being added: ");
		String first = input.nextLine();
                System.out.println("Please enter the last name of the author being added: ");
		String last = input.nextLine();
		thing.insertAuthor(first, last);
	}
	
	//Gathers necessary variables before calling the changeAuthor method
	public static void modWriter(BookAuthorQueries thing) {
                Scanner input = new Scanner(System.in);
		System.out.println("Please enter the first name of the author being edited: ");
		String oFirst = input.nextLine();
                System.out.println("Please enter the last name of the author being edited: ");
		String oLast = input.nextLine();
                System.out.println("Please enter the new first name: ");
		String first = input.nextLine();
                System.out.println("Please enter the new last name: ");
		String last = input.nextLine();
		thing.changeAuthor(oFirst, oLast, first, last);
	}
	
	//Gathers necessary variables before calling the retrieveAuthor method
	public static void getWriter(BookAuthorQueries thing) {
                Scanner input = new Scanner(System.in);
		System.out.println("Please enter the first name of the author you'd like to retrieve from the database: ");
		String first = input.nextLine();
		thing.retrieveAuthor(first);
	}
	
	public static void addTitle(BookAuthorQueries thing) {
                Scanner input = new Scanner(System.in);
		System.out.println("Please enter the ISBN of the book you'd like to add: ");
		int isbn = input.nextInt();
                System.out.println("Please enter the edition of the book you'd like to add: ");
		int edition = input.nextInt();
                System.out.println("Please enter the copyright of the book you'd like to add: ");
		int copyright = input.nextInt();
                System.out.println("Please enter the title of the book you'd like to add: ");
		String title = input.nextLine();
		thing.insertBook(isbn, title, edition, copyright);
	}
	
	//Gathers the necessary variables before calling the changeBook method
	public static void modTitle(BookAuthorQueries thing) {
                Scanner input = new Scanner(System.in);
		System.out.println("Please enter the ISBN  of the book you'd like to edit: ");
		int oisbn = input.nextInt();
                System.out.println("Please enter the edition of the book you'd like to edit: ");
		int oedition = input.nextInt();
                System.out.println("Please enter the copyright of the book you'd like to edit");
		int ocopyright = input.nextInt();
                System.out.println("Please enter the title of the book you'd like to edit: ");
		String otitle = input.nextLine();
		System.out.println("Please enter the ISBN that you'd like the book to have: ");
		int isbn = input.nextInt();
                System.out.println("Please enter the edition you'd like the book to have: ");
		int edition = input.nextInt();
                System.out.println("Please enter the copyright you'd like the book to have: ");
		int copyright = input.nextInt();
                System.out.println("Please enter the title you'd like the book to have: ");
		String title = input.nextLine();
		thing.changeBook(oisbn, otitle, oedition, ocopyright, isbn, title, edition, copyright);
	}
	
	//Gathers the necessary variables before calling the insertAuthorAndBook method
	public static void addWriterAndTitle(BookAuthorQueries thing) {
                Scanner input = new Scanner(System.in);
		System.out.println("Please enter the ISBN of the book you'd like to add: ");
		int isbn = input.nextInt();
                System.out.println("Please enter the first name of the author being added: ");
		String first = input.nextLine();
                System.out.println("Please enter the last name of the author being added: ");
		String last = input.nextLine();
		thing.insertAuthorAndBook(isbn, first, last);
	}
}
