import java.util.Scanner;

public class TripCalculator {
	
	public static void main(String[] args) {
		
		int milesDriven;
		int gallonsUsed;
		int numberOfTrips;
		float milesPerGallon;
		float mileageSum = 0;
		float mileageAverage = 0;
		int sentinel = 0;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("\nPlease enter the number of trips you took: ");
		numberOfTrips = input.nextInt();
		
		while (sentinel < numberOfTrips) {
			System.out.println("\n\nPlease enter the number of miles driven on trip " + sentinel + ": ");
			milesDriven = input.nextInt();
			
			System.out.println("\nPlease enter the number of gallons used on trip " + sentinel + ": ");
			gallonsUsed = input.nextInt();
			
			milesPerGallon = (float) milesDriven / (float) gallonsUsed;
			mileageSum = mileageSum + milesPerGallon;
			System.out.println("\nThe miles per gallon for trip number " + sentinel + " was:" + milesPerGallon);
			
			sentinel++;
		}
		
		mileageAverage = mileageSum / numberOfTrips;
		System.out.println("\n\nThe average miles per gallon for all trips was: " + mileageAverage);
	}
}