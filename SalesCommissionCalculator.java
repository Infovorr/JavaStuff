import java.util.Scanner;

public class SalesCommissionCalculator {
	
	public static void main(String[] args) {
		
		float totalSales = 0;
		int numberOfSales = 0;
		float totalPay = 0;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("\nPlease enter the amount of item one that was sold this week: ");
		numberOfSales = input.nextInt();
		
		totalSales = totalSales + ((float) numberOfSales * 228.88f);
		
		System.out.println("\nPlease enter the amount of item two that was sold this week: ");
		numberOfSales = input.nextInt();
		
		totalSales = totalSales + ((float) numberOfSales * 124.75f);
		
		System.out.println("\nPlease enter the amount of item three that was sold this week: ");
		numberOfSales = input.nextInt();
		
		totalSales = totalSales + ((float) numberOfSales * 88.85f);
		
		System.out.println("\nPlease enter the amount of item four that was sold this week: ");
		numberOfSales = input.nextInt();
		
		totalSales = totalSales + ((float) numberOfSales * 250.76f);
		
		totalPay = 200 + (totalSales * 0.09f);
		
		System.out.println("\n\nTotal earnings for the week are: " + totalPay);
	}
}