
/**
 * Sweetener - child class of FoodItem adds the sweetener supplier to its output
 * 
 * @author Justain Tremblay
 * Student number: 040968930
 */
import java.util.Scanner;

public class Sweetener extends InventoryItem {
	/**
	 * 
	 */
	private String farmName;

	/**
	 * Default Constructor
	 */
	public Sweetener() {
		super();
		farmName = "";
	}

	/**
	 * User inputs the name of the farm supplier
	 * 
	 * @param scanner - Scanner to input the supplier
	 * @return true - returns true when complete
	 */
	@Override
	public boolean addItem(Scanner scanner) {
		if (super.addItem(scanner)) {
			System.out.print("Enter the name of the sweetener supplier: ");
			farmName = scanner.next();
		}
		return true;
	}

	/**
	 * Adds the sweetener supplier to the other data
	 * 
	 * @return string containing the sweetener supplier
	 */
	@Override
	public String toString() {
		return super.toString() + " sweetener supplier: " + farmName;
	}

}