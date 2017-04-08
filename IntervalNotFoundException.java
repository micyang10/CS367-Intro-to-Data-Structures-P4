/**
 * This class defines the exception when interval is not found in the tree.
 * 
 * @author apul
 *
 */
public class IntervalNotFoundException extends Exception {
	public IntervalNotFoundException(String message) {
		System.out.println("Interval: " + message + " not found in the tree.");
	}
}
