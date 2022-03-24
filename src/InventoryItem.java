
/**
 * Receives inputs from user and formats and outputs a string with any food item
 * 
 * @author Justain Tremblay
 * Student number: 040968930
 */
import java.time.LocalDate;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class InventoryItem {

	private int itemCode;

	private String itemName;

	private float itemPrice;

	private Queue<LocalDate> dates;

	private float itemCost;

	private int itemQuantityInStock;

	/**
	 * Default Constructor
	 */
	public InventoryItem() {
		itemCode = 0;
		itemName = "";
		itemPrice = 0.0f;
		itemCost = 0.0f;
		itemQuantityInStock = 0;
		dates = new PriorityQueue<>();
	}

	/**
	 * Reads from the Scanner object passed in and fills the data member fields of
	 * the class with valid data.
	 * 
	 * @param scanner - Scanner to use for input
	 * @return true if all data members were filled, false otherwise
	 */
	public boolean addItem(Scanner scanner) {
		boolean valid = false;

		System.out.print("Enter the name for the item: ");
		itemName = scanner.next();

		// Input the cost
		valid = false;
		while (!valid) {
			System.out.print("Enter the cost of the item: ");
			if (scanner.hasNextFloat()) {
				itemCost = scanner.nextFloat();
				if (itemCost < 0) {
					valid = false;
					System.out.println("Invalid input");
					itemCost = 0;
				} else
					valid = true;
			} else {
				System.out.println("Invalid input");
				scanner.next();
				valid = false;
			}
		}

		// Input the price
		valid = false;
		while (!valid) {
			System.out.print("Enter the sales price of the item: ");
			if (scanner.hasNextFloat()) {
				itemPrice = scanner.nextFloat();
				if (itemPrice < 0) {
					valid = false;
					System.out.println("Invalid input");
					itemPrice = 0;
				} else
					valid = true;
			} else {
				System.out.println("Invalid input");
				scanner.next();
				valid = false;
			}
		}

		// Input quantity
		valid = false;
		while (!valid) {
			System.out.print("Enter the quantity for the item: ");
			if (scanner.hasNextInt()) {
				itemQuantityInStock = scanner.nextInt();
				if (itemQuantityInStock < 0) {
					valid = false;
					System.out.println("Invalid input");
					itemQuantityInStock = 0;
				} else
					valid = true;
			} else {
				System.out.println("Invalid input");
				scanner.next();
				valid = false;
			}
		}

		valid = false;
		while (!valid) {
			int dateArr[] = new int[3];

			System.out.println("Please enter today's date (yyyy-mm-dd) or none");
			String tempDate = scanner.next();
			if (tempDate.equals("none")) {
				for (int i = 0; i < itemQuantityInStock; i++)
					dates.add(LocalDate.MAX);
				
					valid = true;
			} else if (tempDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
				String[] tempArray = tempDate.split("-");

				for (int i = 0; i < 3; i++) {
					dateArr[i] = Integer.parseInt(tempArray[i]);
				}
				for (int i = 0; i < itemQuantityInStock; i++)
					dates.add(LocalDate.of(dateArr[0], dateArr[1], dateArr[2]));
				valid = true;
			} else {
				System.out.println("Invalid date");
				valid = false;
			}
		}

		return true;
	}

	/**
	 * Updates the quantity field by amount (note amount could be positive or
	 * negative)
	 * 
	 * @param amount - what to update by, either can be positive or negative
	 * @param scanner - scanner used for user input
	 * @return Method returns true if successful, otherwise returns false
	 */
	public boolean updateItem(int amount, Scanner scanner) {
		// If you are removing stock, then check that we have enough stock
		if (amount < 0 && itemQuantityInStock < (amount * -1)) {
			return false;
		}
		if(amount < 0) {
			for(int i = 0; i < amount *-1;i++)
				dates.poll();
		}else {
			boolean valid = false;
			while (!valid) {
				int dateArr[] = new int[3];

				System.out.println("Please enter today's date (yyyy-mm-dd) or none");
				String tempDate = scanner.next();

				if(tempDate.equals("none")) {
					for (int i = 0; i < amount; i++)
						dates.add(LocalDate.MAX);
					
						valid = true;
				}else if(tempDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
					String[] tempArray = tempDate.split("-");

					for (int i = 0; i < 3; i++) {
						dateArr[i] = Integer.parseInt(tempArray[i]);
					}
					for (int i = 0; i < amount; i++)
						dates.add(LocalDate.of(dateArr[0], dateArr[1], dateArr[2]));
					valid = true;
				} else {
					System.out.println("Invalid date");
					valid = false;
				}
			}
		}
		
		itemQuantityInStock+=amount;
		return true;

	}

	/**
	 * Compares this object's item code with the one passed in
	 * 
	 * @param item - object to compare with
	 * @return true if the itemCode of the object being acted on and the item object
	 *         parameter are the same value, false otherwise
	 */
	public boolean isEqual(InventoryItem item) {
		return itemCode == item.itemCode;
	}

	/**
	 * Reads a valid itemCode from the Scanner object and returns true/false if
	 * successful
	 * 
	 * @param scanner - Scanner to use for input
	 * @return true if code was filled, false otherwise
	 */
	public boolean inputCode(Scanner scanner) {
		boolean validInput = false;
		while (!validInput) {
			// System.out.print("Enter valid item code: ");
			System.out.print("Enter the code for the item: ");
			if (scanner.hasNextInt()) {
				itemCode = scanner.nextInt();
				validInput = true;
			} else {
				System.out.println("Invalid code");
				// Clear the invalid input
				scanner.next();
			}
		}
		return validInput;
	}
	
	/**
	 * Method that prints the expire list of the selected item
	 */
	public void printExpirySummary() {
		Queue<LocalDate> tempQ = new PriorityQueue<>();
		tempQ.addAll(dates);
		LocalDate temp;
		int count = 0;

		if(itemQuantityInStock == 0) {
			System.out.println("No items in stock");
			return;
		}
		System.out.print(tempQ.peek()+": ");
		temp = tempQ.peek();	

		
		for(int i = 0; i< itemQuantityInStock;i++) {
			if(temp.equals(tempQ.poll())) {
				count++;
			}else {
				System.out.print(count+"\n");
				count = 1;
				System.out.print(tempQ.peek()+": ");
				temp = tempQ.peek();
			}
		}
		System.out.print(count+"\n");
	}

	/**
	 * Method that deletes all expired items
	 * 
	 * @param today - date thats used to delete all expired items
	 */
	public void removeExpiredItems(LocalDate today) {
		while(dates.size() > 0) {
			if(today.isAfter(dates.peek())){
				dates.poll();
				itemQuantityInStock--;
			}else {
				return;
			}
		}
	}

	

	/**
	 * used to output the array into string format
	 * 
	 * @return a string containing all product information
	 */
	@Override
	public String toString() {
		return "Item: " + itemCode + " " + itemName + " cost: $" + String.format("%.2f", itemCost) + " price: $"
				+ String.format("%.2f", itemPrice) + " qty: " + itemQuantityInStock;
	}
}
