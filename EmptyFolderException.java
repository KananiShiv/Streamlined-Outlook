/**
 * The EmptyFolderException class is a custom exception thrown when attempting
 * to perform an operation on an empty folder. It extends the Exception class
 * and provides a constructor to set the exception message. This exception is
 * used to handle cases where an operation cannot be performed due to the folder
 * being empty.
 * 
 * Author: Shiv Kanani
 *         SBU ID: 115171965
 *         Homework #5 for CSE 214, Summer 2023
 */
public class EmptyFolderException extends Exception {

	/**
	 * Constructs a new EmptyFolderException with the specified message.
	 * 
	 * @param message The message of the exception.
	 */
	public EmptyFolderException(String message) {
		super(message);
	}
}
