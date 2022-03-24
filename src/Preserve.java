
/**
 * Preserve - child class of FoodItem adds the jar size to its output
 * 
 * @author Justain Tremblay
 * Student number: 040968930
 */
import java.util.Scanner;

public class Preserve extends InventoryItem {

	private int jarSize;

	/**
	 * Default Constructor
	 */
	public Preserve() {
		super();
		jarSize = 0;
	}

	/**
	 * User input the size of the jar
	 * 
	 * @param scanner - Scanner used to input the item
	 * @return returns true when complete false if incomplete
	 */
	@Override
	public boolean addItem(Scanner scanner) {
		boolean valid = false;
		if (super.addItem(scanner)) {
			// Input quantity
			while (!valid) {
				System.out.print("Enter the size of the jar in millilitres: ");
				if (scanner.hasNextInt()) {
					jarSize = scanner.nextInt();
					if (jarSize < 0) {
						valid = false;
						System.out.println("Invalid input");
						jarSize = 0;
					} else
						valid = true;
				} else {
					System.out.println("Invalid input");
					scanner.next();
					valid = false;
				}
			}
		}
		return true;
	}

	/**
	 * Adds the jar size to the other data
	 * 
	 * @return string containing the jar size
	 */
	@Override
	public String toString() {
		return super.toString() + " size: " + jarSize + "mL";
	}

}
