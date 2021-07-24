import java.util.Scanner;

public class MakeChange {

	// Create a scanner for input
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		// Declare variables
		double itemPrice, cashTendered;
		String money;
		boolean keepGoing = true;
		// boolean enoughMoney;

		while (keepGoing) {

			// Prompt for cost of item and initialize it into itemPrice
			System.out.println("Please enter the price of the item: ");
			itemPrice = scanner.nextDouble();

			// Prompt for money tendered and initialize it into cashTendered
			System.out.println("\nPlease enter the money tendered by the customer: ");
			cashTendered = scanner.nextDouble();

			// Resource Management FTW!
			// scanner.close();

			// Determine if enough money has been tendered and alert the user if not (could
			// be done more simply but I wanted to practice w/ ternary operators and switch
			// statements)
			money = cashTendered >= itemPrice ? cashTendered == itemPrice ? "exact" : "over" : "under";

			switch (money) {
			case "exact":
				System.out.println("\nExact change was given.  No change is necessary");
				break;
			case "over":
				calculateChange(cashTendered - itemPrice);
				break;
			case "under":
				double underPayment = Math.round((itemPrice - cashTendered) * 100);
				underPayment /= 100;
				System.out
						.println("\nInsufficient money tendered. Please pay the remaining balance of $" + underPayment);
			}
			
			scanner.nextLine();
			
			// calls keepGoing to determine whether or not to rerun program
			keepGoing = keepGoing();

		}

	}

	private static boolean keepGoing() {
		boolean keepGoing = true;
		boolean mistyped = true;
		// waits for a distinct affirmative or negative to continue
		while (mistyped) {
			System.out.println("Would you like to process another transaction? (Y or N)");
			String cont = scanner.nextLine();
			switch (cont) {
			case "Y":
			case "y":
			case "yes":
			case "Yes":
			case "YES":
			case "yarp":
			case "Yarp":
			case "YARP":
				mistyped = false;
				keepGoing = true;
				break;
			case "no":
			case "No":
			case "NO":
			case "n":
			case "N":
				System.out.println("\nThank you for using this service.  Have a good day!");
				mistyped = false;
				keepGoing = false;
				break;
			default:
				System.out.println("Invalid selection.  Enter any key to try again");
				System.out.println();
//				scanner.nextLine();
			}
		}
		return keepGoing;
	}

	// Determines if there is bill change as well as coinage and calls the
	// appropriate methods. Once returned from change methods, calls printing method
	private static void calculateChange(double change) {
		double originalChange = change;
		String billsText = "";
		String coinsText = "";
		// pulls the whole dollar value from the change and passes it to billsChange.
		// Then sends the remainder (coin change) to coinsChange
		if (change >= 1.0) {
			// casting change to an integer to separate out the whole number value
			int fullDollars = (int) change;
			billsText = billsChange(fullDollars);
			change -= fullDollars;
		}
		coinsText = coinsChange(change);
		// prints the completed change strings
		printChange(billsText, coinsText, originalChange);
		return;

	}

	// does coin change math and builds string
	private static String coinsChange(double change) {
		String coinsText = "Coins -> ";
		int newChange = (int) (Math.round(change * 100));
//		System.out.println(newChange+" "+change);
		int quarters, dimes, nickels, pennies;

		quarters = (newChange / 25);
		newChange = newChange % 25;
		dimes = (newChange / 10);
		newChange = newChange % 10;
		nickels = (newChange / 5);
		newChange = newChange % 5;
		pennies = (newChange / 1);
		newChange = newChange % 1;

		if (quarters > 0)
			coinsText += ("Quarters:" + quarters + " ");
		if (dimes > 0)
			coinsText += ("Dimes:" + dimes + " ");
		if (nickels > 0)
			coinsText += ("Nickels:" + nickels + " ");
		if (pennies > 0)
			coinsText += ("Pennies:" + pennies + " ");

		return coinsText;
	}

	// does bill change math and builds string
	private static String billsChange(int change) {
		String billsText = "Bills -> ";
		int twenties, tens, fives, ones;

		twenties = (change / 20);
		change %= 20;
		tens = (change / 10);
		change %= 10;
		fives = (change / 5);
		change %= 5;
		ones = (change / 1);
		change %= 1;

		if (twenties > 0)
			billsText += ("$20: " + twenties + " ");
		if (tens > 0)
			billsText += ("$10: " + tens + " ");
		if (fives > 0)
			billsText += ("$5: " + fives + " ");
		if (ones > 0)
			billsText += ("$1: " + ones + " ");

		return billsText;
	}

	// prints the string(S)
	private static void printChange(String billsText, String coinsText, double originalChange) {
		System.out.printf("\n\nThis is the correct change to dispense for $%.2f:\n\n", originalChange);
		System.out.println(billsText);
		System.out.println(coinsText);
		System.out.println();
		System.out.println();
		return;
	}
}