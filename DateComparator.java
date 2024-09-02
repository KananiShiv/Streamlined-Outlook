import java.util.Comparator;

/**
 * The DateComparator class implements the Comparator interface to provide a
 * custom comparison logic for sorting Email objects based on their timestamps.
 * The comparison can be done in either ascending or descending order.
 * 
 * Author: Shiv Kanani
 *         SBU ID: 115171965
 *         Homework #5 for CSE 214, Summer 2023
 */

public class DateComparator implements Comparator<Email> {

	private boolean ascending;

	/**
	 * Constructs a new DateComparator with the specified sorting order.
	 * 
	 * @param ascending True if sorting should be in ascending order, false
	 *                  otherwise.
	 */
	public DateComparator(boolean ascending) {
		this.ascending = ascending;
	}

	/**
	 * Compares two Email objects based on their timestamps.
	 * 
	 * @param emailOne The first Email object to compare.
	 * @param emailTwo The second Email object to compare.
	 * @return A positive integer if emailOne's timestamp is later, a negative
	 *         integer if emailTwo's timestamp is later, or 0 if the timestamps are
	 *         equal.
	 */
	public int compare(Email emailOne, Email emailTwo) {
		Email e1 = (Email) emailOne;
		Email e2 = (Email) emailTwo;
		int result = e1.getTimestamp().compareTo(e2.getTimestamp());
		return ascending ? result : -result;
	}
}
