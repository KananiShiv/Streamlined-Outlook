
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * The Mailbox class represents a mailbox that contains folders for organizing
 * emails. It allows adding, removing, composing, and managing emails and
 * folders.
 * 
 * Author: Shiv Kanani
 *         SBU ID: 115171965
 *         Homework #5 for CSE 214, Summer 2023
 */
public class Mailbox implements Serializable {

	/**
     * The special inbox folder.
     */
    private Folder inbox;

    /**
     * The special trash folder.
     */
    private Folder trash;

    /**
     * List of user-created folders.
     */
    private ArrayList<Folder> folders;

    /**
     * Single instance of the mailbox.
     */
    public static Mailbox mailbox;

	/**
	 * Default constructor for creating an empty Mailbox object.
	 */
	public Mailbox() {
		inbox = new Folder();
		trash = new Folder();
		folders = new ArrayList<Folder>();
	}

	/**
	 * Main method for running the mailbox application.
	 * 
	 * @param args Command line arguments.
	 * @throws Exception If an exception occurs during program execution.
	 */

	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		boolean fileWasFound = false;

		try {
			FileInputStream file = new FileInputStream("mailbox.obj");
			ObjectInputStream fin = new ObjectInputStream(file);
			mailbox = (Mailbox) fin.readObject();
			file.close();
			fileWasFound = true;
		} catch (IOException a) {
			System.out.println("Previous save not found, starting with an empty mailbox.");
			fileWasFound = false;
		} catch (ClassNotFoundException c) {
			System.out.println("Class was not found");
			fileWasFound = false;
		}

		if (!fileWasFound) {
			boolean flag = true;
			mailbox = new Mailbox();
			while (flag) {
				System.out.println();
				mailbox.printMailbox();

				System.out.println("A - Add folder");
				System.out.println("R - Remove folder");
				System.out.println("C - Compose email");
				System.out.println("F - View Folder");
				System.out.println("I - View Inbox");
				System.out.println("T - View Trash");
				System.out.println("E - Empty Trash");
				System.out.println("Q - Quit");
				System.out.println("");
				System.out.println("Enter a user option:");
				String option = input.nextLine();

				switch (option.toUpperCase()) {
				case "A":
					System.out.println("Enter folder name: ");
					String folderName = input.nextLine();
					Folder newFolder = new Folder();
					newFolder.setName(folderName);
					mailbox.addFolder(newFolder);
					System.out.println("Folder added.");
					break;
				case "R":
					System.out.println("Enter folder name to remove: ");
					String folderToRemove = input.nextLine();
					mailbox.deleteFolder(folderToRemove);
					break;
				case "C":
					mailbox.composeEmail();
					System.out.println("Email successfully added to Inbox.");
					break;
				case "F":
					System.out.println("Enter folder name: ");
					String folderToView = input.nextLine();
					Folder selectedFolder = mailbox.getFolder(folderToView);
					System.out.println(selectedFolder.getName());
					System.out.println("");
					selectedFolder.printFolder();
					System.out.println("");
					if (selectedFolder != null) {
						folderSubMenu(selectedFolder, input);
					}
					System.out.println("");

					break;
				case "I":
					mailbox.inbox.printFolder();
					System.out.println("");
					folderSubMenu(mailbox.inbox, input);
					break;
				case "T":
					mailbox.trash.printFolder();
					System.out.println("");
					folderSubMenu(mailbox.trash, input);
					break;
				case "E":
					try {
						mailbox.clearTrash();
						System.out.println("Trash folder emptied.");
					} catch (EmptyFolderException e) {
						System.out.println("Trash folder is already empty.");
					}
					break;
				case "Q":
					try {
						FileOutputStream file = new FileOutputStream("mailbox.obj");
						ObjectOutputStream fout = new ObjectOutputStream(file);
						fout.writeObject(mailbox);
						fout.close();
						System.out.println("Program successfully exited and mailbox saved.");
					} catch (IOException a) {
						System.out.println("Error saving mailbox.");
					}

					flag = false;
					break;
				default:
					System.out.println("Invalid option. Please try again.");
				}
			}
		}
	}

	/**
	 * Displays the submenu for a specific folder, allowing the user to perform
	 * various actions.
	 *
	 * @param folder The folder for which the submenu is displayed.
	 * @param input  The Scanner object to capture user input.
	 * @throws Exception If an exception occurs during execution.
	 */

	public static void folderSubMenu(Folder folder, Scanner input) throws Exception {
		boolean folderMenuFlag = true;
		while (folderMenuFlag) {
			System.out.println();
			System.out.println("M - Move email");
			System.out.println("D - Delete email");
			System.out.println("V - View email contents");
			System.out.println("SA - Sort by subject ascending");
			System.out.println("SD - Sort by subject descending");
			System.out.println("DA - Sort by date ascending");
			System.out.println("DD - Sort by date descending");
			System.out.println("R - Return to main menu");

			System.out.println("Enter a user option:");
			String folderOption = input.nextLine();

			switch (folderOption.toUpperCase()) {
			case "M":
				System.out.println("Enter the index of the email to move: ");
				int emailIndex = input.nextInt();
				input.nextLine();
				System.out.println("Folders:");
				System.out.println("Inbox");
				System.out.println("Trash");
				for (Folder availableFolder : mailbox.folders) {
					System.out.println(availableFolder.getName());
				}
				System.out.println(
						"Select a folder to move \"" + mailbox.inbox.getEmails(emailIndex).getSubject() + "\" to ");

				String targetFolderName = input.nextLine();
				Folder targetFolder = mailbox.getFolder(targetFolderName);
				if (targetFolder != null) {
					mailbox.moveEmail(mailbox.inbox.getEmails(emailIndex), targetFolder);
					System.out.println("\"" + mailbox.inbox.getEmails(emailIndex).getSubject()
							+ "\" successfully moved to Other Folder.");
					System.out.println("");
					mailbox.inbox.removeEmail(emailIndex);
				} else {
					System.out.println("Target folder not found. Operation canceled.");
					System.out.println("");
				}
				folder.printFolder();

				break;
			case "D":
				System.out.println("Enter email index to delete: ");
				int emailIndexForTrash = input.nextInt();
				input.nextLine();
				Email removeEmail = folder.getEmails(emailIndexForTrash);
				if (removeEmail != null) {
					mailbox.deleteEmail(folder.getEmails(emailIndexForTrash));
					folder.removeEmail(emailIndexForTrash);
				} else {
					System.out.println("Target folder not found. Operation canceled.");
					System.out.println("");
				}
				folder.printFolder();

				break;
			case "V":
				System.out.println("Enter email index: ");
				int emailToView = Integer.parseInt(input.nextLine());
				if (emailToView > 0 && emailToView <= folder.getCounter()) {
					Email email = folder.getEmails(emailToView);
					System.out.println("To: " + email.getTo());
					System.out.println("CC: " + email.getCc());
					System.out.println("BCC: " + email.getBcc());
					System.out.println("Subject: " + email.getSubject());
					System.out.println("Body: " + email.getBody());
				} else {
					System.out.println("Invalid email index.");
				}
				System.out.println("");
				folder.printFolder();
				break;
			case "SA":
				folder.sortBySubjectAscending();
				break;
			case "SD":
				folder.sortBySubjectDescending();
				break;
			case "DA":
				folder.sortByDateAscending();
				break;
			case "DD":
				folder.sortByDateDescending();
				break;
			case "R":
				folderMenuFlag = false;
				break;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}

	/**
	 * Adds a new folder to the mailbox's list of folders.
	 *
	 * @param folder The folder to be added.
	 */

	public void addFolder(Folder folder) {

		for (int i = 0; i < folders.size(); i++) {
			if (folders.get(i).getName().equals(folder.getName())) {
				System.out.println("A folder with that name already exists! Please try again.");
				return;
			}
		}

		folders.add(folder);
		System.out.println("The folder has been successfully added.");

	}

	/**
	 * Deletes a folder with the specified name from the mailbox's list of folders.
	 * If the folder is found and removed, a success message is displayed. If the
	 * folder is not found, a message indicating the absence of the folder is
	 * displayed.
	 *
	 * @param name The name of the folder to be deleted.
	 */

	public void deleteFolder(String name) {
		for (int i = 0; i < folders.size(); i++) {
			if (folders.get(i).getName().equals(name)) {
				folders.remove(i);
				System.out.println("The folder has been successfully removed!");
			} else
				System.out.println("The folder you want to remove does not exist!");
		}
	}

	/**
	 * Allows the user to compose and add a new email to the inbox. The user is
	 * prompted to enter recipient addresses, subject, and body.
	 */

	public void composeEmail() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter recipient (To): ");
		String to = scan.nextLine();
		System.out.println("Enter carbon copy recipients (CC): ");
		String cc = scan.nextLine();
		System.out.println("Enter blind carbon copy recipients (BCC): ");
		String bcc = scan.nextLine();
		System.out.println("Enter subject line: ");
		String subject = scan.nextLine();
		System.out.println("Enter body: ");
		String body = scan.nextLine();

		Email newEmail = new Email(to, cc, bcc, subject, body);
		inbox.addEmail(newEmail);
	}

	/**
	 * Moves the specified email to the mailbox's trash folder. Displays a
	 * confirmation message after the email is moved to trash.
	 *
	 * @param email The email to be deleted and moved to trash.
	 */

	public void deleteEmail(Email email) {

		trash.addEmail(email);
		System.out.println("The email has been deleted and moved to trash.");
	}

	/**
	 * Clears all emails from the mailbox's trash folder. Throws an
	 * EmptyFolderException if the trash folder is already empty.
	 *
	 * @throws EmptyFolderException If the trash folder is already empty.
	 */

	public void clearTrash() throws EmptyFolderException {
		for (int i = 0; i < mailbox.trash.getCapacity(); i++) {
			mailbox.trash.removeEmail(i + 1);
		}
	}

	/**
	 * Moves the specified email to the target folder. If the target folder is
	 * found, the email is moved to it. If the target folder is not found, the email
	 * is added to the inbox instead.
	 *
	 * @param email  The email to be moved.
	 * @param target The target folder to which the email is moved.
	 */

	public void moveEmail(Email email, Folder target) {
		for (int i = 0; i < folders.size(); i++) {
			if (folders.get(i).getName().equals(target.getName())) {
				target.addEmail(email);
			}

			else {
				inbox.addEmail(email);
				System.out.println("The folder was not found, the email was added to the inbox");
			}
		}
	}

	/**
	 * Retrieves a folder with the specified name from the mailbox's list of
	 * folders. If the folder with the given name is found, it is returned. If the
	 * folder is not found, an Exception is thrown with an error message.
	 *
	 * @param name The name of the folder to retrieve.
	 * @return The retrieved Folder object with the specified name.
	 * @throws Exception If the folder with the given name is not found.
	 */

	public Folder getFolder(String name) throws Exception {

		for (int i = 0; i < folders.size(); i++) {
			if (folders.get(i).getName().equals(name)) {
				return folders.get(i);
			} else
				throw new Exception("The folder was not found");

		}
		return null;
	}
	

	/**
	 * Prints the contents of the mailbox, including the inbox, trash, and user-created folders.
	 * The method displays the names of these folders in a formatted manner.
	 */
	
	public void printMailbox() {
	    System.out.println("Mailbox:");
	    System.out.println("--------");
	    System.out.println("Inbox");
	    System.out.println("Trash");

	    for (int i = 0; i < folders.size(); i++) {
	        System.out.println(folders.get(i).getName());
	    }
	    System.out.println("");
	}


}
