import java.util.regex.Pattern;
import java.util.Scanner;
import java.time.LocalDate;

/**
 * program that stores food items to buy and sell as well as checking the expire date
 * 
 * @author Justain Tremblay 
 *
 */
public class ProductInventory {

	/**
	 * outputs menu
	 */
	public static void displayMenu() {
		System.out.println("Please select one of the following:");
		System.out.println("1: Add Item to Inventory");
		System.out.println("2: Display Current Inventory");
		System.out.println("3: Buy Item(s)");
		System.out.println("4: Sell Item(s)");
		System.out.println("5: Search for Item");
		System.out.println("6: Remove Expired Items");
		System.out.println("7: Print Expiry");
		System.out.println("8: Change Today's Date");
		System.out.println("9: To Exit");
		System.out.print("> ");
	}

	public static void main(String[] args) {
		Inventory inventory = new Inventory();
		int choice = 0;
		Scanner scanner = new Scanner(System.in);
		LocalDate date = LocalDate.now();
		scanner.useDelimiter(Pattern.compile("[\\r\\n]+"));
		while (choice != 9) {
			try {
				displayMenu();
				if (scanner.hasNext(Pattern.compile("[1-9]"))) {
					choice = scanner.nextInt();
					switch (choice) {
					case 1: // Add Item
						if (!inventory.addItem(scanner))
							System.out.println("Error...could not add item");
						break;
					case 2: // Display Current Inventory
						System.out.println(inventory);
						break;
					case 3: // Buy Items
						if (!inventory.updateQuantity(scanner, true))
							System.out.println("Error...could not buy item");
						break;
					case 4: // Sell Items
						if (!inventory.updateQuantity(scanner, false))
							System.out.println("Error...could not sell item");
						break;
					case 5:// Search for item
						if (!inventory.searchItem(scanner))
							System.out.println("Error...could not find item");
						break;
					case 6:// Remove expired items
						inventory.removeExpiry(date);
						break;
					case 7:// Print expired items
						if (!inventory.printExpirySummary(scanner))
							System.out.println("Error...could not find item");
						break;
					case 8:// Change today's date
						int dateArr[] = new int[3];
						System.out.println("Please enter today's date (yyyy-mm-dd)");
						String tempDate = scanner.next();

						if (tempDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
							String[] tempArray = tempDate.split("-");

							for (int i = 0; i < 3; i++) {
								dateArr[i] = Integer.parseInt(tempArray[i]);
							}
							date = LocalDate.of(dateArr[0], dateArr[1], dateArr[2]);


						} else {
							System.out.println("Invalid date");
						}

						break;
					case 9: // exit
						System.out.println("Have a great day!");
						break;
					default: // Should never get here
						System.out.println("Something went wrong");
						break;
					}
				} else {
					System.out.println("Incorrect value entered");
					scanner.next();
				}
			} catch (Exception e) {
				System.out.println("Error Occurred: " + e.getMessage());
			}
		}
		scanner.close();
	}
}
