
/**
 * Fruit - child class of FoodItem adds the orchard supplier to its output
 * 
 * @author Justain Tremblay
 * 
 */
import java.util.Scanner;

public class Fruit extends InventoryItem {

	private String orchardName;

	/**
	 * Default Constructor
	 */
	public Fruit() {
		super();
		orchardName = "";
	}

	/**
	 * Adds the orchard supplier to the other data
	 * 
	 * @return string containing the orchard supplier
	 */
	@Override
	public String toString() {
		return super.toString() + " orchard supplier: " + orchardName;
	}

	/**
	 * User input the name of the orchard supplier
	 * 
	 * @param scanner - Scanner used to input the item
	 * @return true - returns true when complete
	 */
	@Override
	public boolean addItem(Scanner scanner) {
		if (super.addItem(scanner)) {
			System.out.print("Enter the name of the orchard supplier: ");
			orchardName = scanner.next();
		}
		return true;
	}
}
