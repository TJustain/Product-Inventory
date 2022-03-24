
/**
 * Inventory - handles various elements of the inventory such as creating, adding, removing and outputting the data
 * 
 * @author Justain Tremblay
 * Student number: 040968930
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Inventory {
	/**
	 * ArrayList of FoodItems that represents our inventory
	 */
	private ArrayList<InventoryItem> inventory;
	
	/**
	 * Number of items that a user has entered
	 */
	private int numItems;

	/**
	 * Default Constructor
	 */
	public Inventory() {
		inventory = new ArrayList<InventoryItem>();
	}

	/**
	 * Reads from the Scanner object passed in and fills the data member fields of
	 * the class with valid data.
	 * 
	 * @param scanner - Scanner to use for input
	 * @return true if all data was populated, false otherwise
	 * 
	 */
	public boolean addItem(Scanner scanner) {
		boolean valid = false;
		InventoryItem item = null;
		while (!valid) {
			System.out.print("Do you wish to add a fruit(f), vegetable(v), preserve(p) or a sweetener(s)? ");
			if (scanner.hasNext(Pattern.compile("[fFvVpPsS]"))) {
				String choice = scanner.next();
				switch (choice.toLowerCase()) {
				case "f":
					item = new Fruit();
					break;
				case "v":
					item = new Vegetable();
					break;
				case "p":
					item = new Preserve();
					break;
				case "s":
					item = new Sweetener();
					break;
				default: // Should not get here.
					item = new InventoryItem();
					break;
				}
				valid = true;
			} else {
				System.out.println("Invalid entry");
				scanner.next();
				valid = false;
			}
		}
		if (item.inputCode(scanner)) {
			if (alreadyExists(item) < 0) {
				if (item.addItem(scanner)) {
					//System.out.println(item.getCode());
					inventory.add(item);
					numItems++;
					return true;
				}
				return false;
			} else {
				System.out.println("Item code already exists");
				return false;
			}
		}
		return true;
	}

	/**
	 * Search for a food item and see if it is already stored in the inventory
	 * 
	 * @param item - FoodItem to look for
	 * @return - The index of item if it is found, -1 otherwise
	 */
	public int alreadyExists(InventoryItem item) {
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i).isEqual(item))
				return i;
		}
		return -1;
	}
	

	/**
	 * Update the quantity stored in the food item
	 * 
	 * @param scanner   - Input device to use
	 * @param buyOrSell - If we are to add to quantity true or remove false
	 * @return false - returns false if the index is -1
	 */
	public boolean updateQuantity(Scanner scanner, boolean buyOrSell) {
		// If there are no items then we can't update, return
		if (numItems == 0)
			return false;

		InventoryItem temp = new InventoryItem();
		temp.inputCode(scanner);
		int index = alreadyExists(temp);
		if (index != -1) {
			String buySell = buyOrSell ? "buy" : "sell";
			System.out.print("Enter valid quantity to " + buySell + ": ");
			if (scanner.hasNextInt()) {
				int amount = scanner.nextInt();
				if (amount > 0) {
					return inventory.get(index).updateItem(buyOrSell ? amount : amount * -1, scanner);
				} else {
					System.out.println("Invalid quantity...");
				}
			} else {
				System.out.println("Invalid quantity...");
			}
		}
		return false;
	}
	/**
	 * Method that prints the expire dates of the items
	 * 
	 * @param scanner - used for user to input the desired item code
	 * @return true if the index is found false if the index is not found
	 */
	public boolean printExpirySummary(Scanner scanner) {
		InventoryItem temp = new InventoryItem();
		temp.inputCode(scanner);
		int index = alreadyExists(temp);
		if (index != -1) {
			System.out.println(inventory.get(index));
			inventory.get(index).printExpirySummary();
			return true;
		}
		return false;
	}
	
	public boolean searchItem(Scanner scanner) {
		InventoryItem temp = new InventoryItem();
		temp.inputCode(scanner);
		int index = alreadyExists(temp);
		if (index != -1) {
			System.out.println(inventory.get(index));
			return true;
		}
		return false;
		
	}
	
	public void removeExpiry(LocalDate today) {
		for(int i=0;i<inventory.size();i++) {
			inventory.get(i).removeExpiredItems(today);
		}
		
	}

	/**
	 * used to output the array into string format
	 * 
	 * @return returnString - The whole array as a string
	 */
	@Override
	public String toString() {
		String returnString = "Inventory:\n";
		for (int i = 0; i < numItems; i++)
			returnString += inventory.get(i).toString() + "\n";
		return returnString;
	}
}
