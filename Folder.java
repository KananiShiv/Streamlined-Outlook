import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The Folder class represents a collection of Email objects and provides
 * methods to manage and manipulate the emails within the folder. It implements
 * the Serializable interface to allow objects of this class to be serialized.
 * 
 * Author: Shiv Kanani
 *         SBU ID: 115171965
 *         Homework #5 for CSE 214, Summer 2023
 */

public class Folder implements Serializable {

	/**
     * The list of emails stored in the folder.
     */
    private ArrayList<Email> emails;

    /**
     * The name of the folder.
     */
    private String name;

    /**
     * The current sorting method used for emails.
     */
    private String currentSortingMethod;

    /**
     * The count of emails in the folder.
     */
    private int counter;

	/**
	 * Default constructor for creating a Folder object. Initializes the sorting
	 * method to descending by date and sets the counter to 0.
	 */
	public Folder() {
		currentSortingMethod = "sortByDateDescending";
		counter = 0;
		emails = new ArrayList<Email>();
	}

	/**
	 * Adds an Email object to the folder and maintains the sorting order based on
	 * the current sorting method.
	 * 
	 * @param email The Email object to be added to the folder.
	 */
	public void addEmail(Email email) {
		emails.add(email);

		if (currentSortingMethod.equals("sortBySubjectAscending")) {
			sortBySubjectAscending();
		} else if (currentSortingMethod.equals("sortBySubjectDescending")) {
			sortBySubjectDescending();
		} else if (currentSortingMethod.equals("sortByDateAscending")) {
			sortByDateAscending();
		} else if (currentSortingMethod.equals("sortByDateDescending")) {
			sortByDateDescending();
		}

		counter++;
	}

	/**
	 * Removes an Email object from the folder at the specified index and decrements
	 * the counter.
	 * 
	 * @param index The index of the Email object to be removed.
	 * @return The removed Email object.
	 * @throws EmptyFolderException If the folder is empty or the index is out of
	 *                              range.
	 */
	public Email removeEmail(int index) throws EmptyFolderException {
		if (emails.size() != 0 && index > 0 && index <= emails.size()) {
			counter--;
			return emails.remove(index - 1);
		} else {
			throw new EmptyFolderException("The list is empty or invalid index!");
		}
	}

	/**
	 * Sorts the emails in ascending order based on the subject.
	 */
	public void sortBySubjectAscending() {
		Collections.sort(emails, new SubjectComparator(true));
		currentSortingMethod = "sortBySubjectAscending";
	}

	/**
	 * Sorts the emails in descending order based on the subject.
	 */
	public void sortBySubjectDescending() {
		Collections.sort(emails, new SubjectComparator(false));
		currentSortingMethod = "sortBySubjectDescending";
	}

	/**
	 * Sorts the emails in ascending order based on the date.
	 */
	public void sortByDateAscending() {
		Collections.sort(emails, new DateComparator(true));
		currentSortingMethod = "sortByDateAscending";
	}

	/**
	 * Sorts the emails in descending order based on the date.
	 */
	public void sortByDateDescending() {
		Collections.sort(emails, new DateComparator(false));
		currentSortingMethod = "sortByDateDescending";
	}

	/**
	 * Gets the Email object at the specified index.
	 * 
	 * @param index The index of the Email object to retrieve.
	 * @return The Email object at the specified index.
	 */
	public Email getEmails(int index) {
		return emails.get(index - 1);
	}

	/**
	 * Sets the list of Email objects in the folder.
	 * 
	 * @param emails The list of Email objects to set.
	 */
	public void setEmails(ArrayList<Email> emails) {
		this.emails = emails;
	}

	/**
	 * Gets the name of the folder.
	 * 
	 * @return The name of the folder.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the folder.
	 * 
	 * @param name The name to set for the folder.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the current sorting method used for emails.
	 * 
	 * @return The current sorting method.
	 */
	public String getCurrentSortingMethod() {
		return currentSortingMethod;
	}

	/**
	 * Sets the current sorting method for emails.
	 * 
	 * @param currentSortingMethod The sorting method to set.
	 */
	public void setCurrentSortingMethod(String currentSortingMethod) {
		this.currentSortingMethod = currentSortingMethod;
	}

	/**
	 * Gets the counter value indicating the number of emails in the folder.
	 * 
	 * @return The counter value.
	 */
	public int getCounter() {
		return counter;
	}

	/**
	 * Sets the counter value indicating the number of emails in the folder.
	 * 
	 * @param counter The counter value to set.
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

	/**
	 * Gets the capacity of the folder, which is equal to the counter value.
	 * 
	 * @return The capacity of the folder.
	 */
	public int getCapacity() {
		return getCounter();
	}

	/**
	 * Prints the list of emails in a formatted manner, showing their index, time,
	 * date, and subject. If the folder is empty, a message indicating that the
	 * folder is empty is printed.
	 */
	public void printFolder() {
		if (emails.isEmpty()) {
			System.out.println("The specified folder is empty.");
		} else {
			System.out.println("Index |          Time         | Subject");
			System.out.println("---------------------------------------");

			for (int i = 0; i < emails.size(); i++) {
				Email email = emails.get(i);
				String timeFormatted = String.format("%1$tI:%1$tM%1$Tp", email.getTimestamp());
				String dateFormatted = String.format("%1$tm/%1$td/%1$tY", email.getTimestamp());
				System.out.printf("  %-3d |   %7s %10s  | %-40s%n", i + 1, timeFormatted, dateFormatted,
						email.getSubject());
			}
		}
	}
}
