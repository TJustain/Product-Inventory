
/**
 * Vegetable - child class of FoodItem adds the farm supplier to its output
 * 
 * @author Justain Tremblay
 * Student number: 040968930
 */
import java.util.Scanner;

public class Vegetable extends InventoryItem {

	private String farmName;

	/**
	 * Default Constructor
	 */
	public Vegetable() {
		super();
		farmName = "";
	}

	/**
	 * User input the name of the farm supplier
	 * 
	 * @param scanner - Scanner used to input the item
	 * @return true - returns true when complete
	 */
	@Override
	public boolean addItem(Scanner scanner) {
		if (super.addItem(scanner)) {
			System.out.print("Enter the name of the farm supplier: ");
			farmName = scanner.next();
		}
		return true;
	}

	/**
	 * Adds the farm supplier to the other data
	 * 
	 * @return string containing the farm supplier
	 */
	@Override
	public String toString() {
		return super.toString() + " farm supplier: " + farmName;
	}
}
