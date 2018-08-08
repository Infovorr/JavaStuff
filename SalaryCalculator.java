import java.util.Scanner;

public class SalaryCalculator {
	
	public static void main(String[] args) {
		
		float employeeOneRate;
		float employeeTwoRate;
		float employeeThrRate;
		int employeeOneHours;
		int employeeTwoHours;
		int employeeThrHours;
		float employeeOnePay;
		float employeeTwoPay;
		float employeeThrPay;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("\nEnter the hourly rate for employee one: ");
		employeeOneRate = input.nextFloat();
		
		System.out.println("\nEnter the hourly rate for employee two: ");
		employeeTwoRate = input.nextFloat();
		
		System.out.println("\nEnter the hourly rate for employee three: ");
		employeeThrRate = input.nextFloat();
		
		System.out.println("\nEnter the hours worked by employee one: ");
		employeeOneHours = input.nextInt();
		
		System.out.println("\nEnter the hours worked by employee two: ");
		employeeTwoHours = input.nextInt();
		
		System.out.println("\nEnter the hours worked by employee three: ");
		employeeThrHours = input.nextInt();
		
		if (employeeOneHours <= 30) {
			employeeOnePay = employeeOneRate * employeeOneHours;
		}
		else {
			employeeOnePay = (employeeOneRate * 30) + (((employeeOneHours - 30) * 1.5f) * employeeOneRate);
		}
		
		if (employeeTwoHours <= 30) {
			employeeTwoPay = employeeTwoRate * employeeTwoHours;
		}
		else {
			employeeTwoPay = (employeeTwoRate * 30) + (((employeeTwoHours - 30) * 1.5f) * employeeTwoRate);
		}
		
		if (employeeThrHours <= 30) {
			employeeThrPay = employeeThrRate * employeeThrHours;
		}
		else {
			employeeThrPay = (employeeThrRate * 30) + (((employeeThrHours - 30) * 1.5f) * employeeThrRate);
		}
		
		
		System.out.println("\n\nGross pay for employee one is: " + employeeOnePay);
		
		System.out.println("\nGross pay for employee two is: " + employeeTwoPay);
		
		System.out.println("\nGross pay for employee three is: " + employeeThrPay);
	}
}