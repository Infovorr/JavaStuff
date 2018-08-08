import java.util.Scanner;

public class CreditLimitCalculator {
	
	public static void main(String[] args) {
		
		int account;
		int balance = 0;
		int debits = 0;
		int credits = 0;
		int limit = 0;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("\nPlease enter an account number: ");
		account = input.nextInt();
		
		System.out.println("\nPlease enter the initial balance for the month: ");
		balance = input.nextInt();
		
		System.out.println("\nPlease enter the total charges for the month: ");
		debits = input.nextInt();
		
		System.out.println("\nPlease enter the total credits for the month: ");
		credits = input.nextInt();
		
		System.out.println("\nPlease enter the credit limit for the month: ");
		limit = input.nextInt();
		
		balance = balance + debits - credits;
		System.out.println("\n\nThe balance is now: " + balance);
		
		if (balance > limit) {
			System.out.println("\nCredit limit exceeded.");
		}
	}
}