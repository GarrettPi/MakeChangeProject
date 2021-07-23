import java.util.Scanner;

public class MakeChange {

	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		// Create a scanner for input

		// Declare variables
		double itemPrice, cashTendered;
		String money;
		// boolean enoughMoney;

		// Prompt for cost of item and initialize it into itemPrice
		System.out.println("Please enter the price of the item: ");
		itemPrice = scanner.nextDouble();

		// Prompt for money tendered and initialize it into cashTendered
		System.out.println("\nPlease enter the money tendered by the customer: ");
		cashTendered = scanner.nextDouble();

		scanner.close();

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
			System.out.println("\nInsufficient money tendered. Please pay the remaining balance of $" + underPayment);
			// break;
		}

	}

	// Determines if there is bills change as well as coinage. Once returned from
	// change methods, calls printing method
	public static void calculateChange(double change) {
		double originalChange = change;
		String billsText = "";
		String coinsText = "";
		// pulls the whole dollar value from the change and passes it to billsChange.
		// Then sends the remainder (coin change) to coinsChange
		if (change >= 1.0) {
			//casting change to an integer to separate out the whole number value
			int fullDollars = (int)change;
			billsText = billsChange(fullDollars);
			change -= fullDollars;
		}
		coinsText = coinsChange(change);
		// prints the completed change strings
		printChange(billsText, coinsText, originalChange);

	}

	// does coin change math and builds string
	private static String coinsChange(double change) {
		String coinsText = "";
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
			coinsText += ("Quarters:" + quarters+" ");
		if (dimes > 0)
			coinsText += ("Dimes:" + dimes+" ");
		if (nickels > 0)
			coinsText += ("Nickels:" + nickels+" ");
		if (pennies > 0)
			coinsText += ("Pennies:" + pennies+" ");

		return coinsText;
	}

	// does bill change math and builds string
	private static String billsChange(int change) {
		String billsText = "";
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
			billsText += ("Twenties: " + twenties+" ");
		if (tens > 0)
			billsText += ("Tens: " + tens+" ");
		if (fives > 0)
			billsText += ("Fives: " + fives+" ");
		if (ones > 0)
			billsText += ("Ones: " + ones+" ");

		return billsText;
	}

	// prints the string(S)
	private static void printChange(String billsText, String coinsText, double originalChange) {
		System.out.printf("\n\nThis is the correct change to dispense for $%.2f:\n", originalChange);
		System.out.println(billsText);
		System.out.println(coinsText);
	}
}