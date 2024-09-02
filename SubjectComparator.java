import java.util.Comparator;

/**
 * The SubjectComparator class implements the Comparator interface to provide a
 * custom comparison logic for sorting Email objects based on their subjects.
 * The comparison can be done in either ascending or descending order.
 * 
 * Author: Shiv Kanani
 *         SBU ID: 115171965
 *         Homework #5 for CSE 214, Summer 2023
 */

public class SubjectComparator implements Comparator<Email> {

	private boolean ascending;

	/**
	 * Constructs a new SubjectComparator with the specified sorting order.
	 * 
	 * @param ascending True if sorting should be in ascending order, false
	 *                  otherwise.
	 */
	public SubjectComparator(boolean ascending) {
		this.ascending = ascending;
	}

	/**
	 * Compares two Email objects based on their subjects.
	 * 
	 * @param emailOne The first Email object to compare.
	 * @param emailTwo The second Email object to compare.
	 * @return A positive integer if emailOne's subject is greater, a negative
	 *         integer if emailTwo's subject is greater, or 0 if the subjects are
	 *         equal.
	 */
	public int compare(Email emailOne, Email emailTwo) {
		Email e1 = (Email) emailOne;
		Email e2 = (Email) emailTwo;
		int result = e1.getSubject().compareTo(e2.getSubject());
		return ascending ? result : -result;
	}
}
