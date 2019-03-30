package onlineBook;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OnlineMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DbOperations db = new DbOperations();
		System.out.println("Welcome To Online Book Store");
		System.out.println("----------------------------------");
		System.out.println("1. Manager");
		System.out.println("2. Buyer");
		System.out.println("----------------------------------");

		int choice = 0;
		while (true) {
			try {
				System.out.println("Please Select your choice  :");
				choice = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				sc.next();
				System.out.println("Entered data is invalid!!!! Please re-enter the field");
				System.out.println("--------------------------------------------------------");
			}
		}

		switch (choice) {
		case 1:
			System.out.println("********--------********");
			System.out.println("Please enter Username :");
			String uname = sc.next();
			while (!uname.matches("[a-zA-Z]+")) {
				System.out.println("Please Enter the valid Username :");
				uname = sc.next();
			}
			System.out.println("********--------********");
			System.out.println("Please enter Password :");
			String pass = sc.next();
			System.out.println("---------------------------");
			boolean check = db.validateAdmin(uname, pass);

			if (check) {
				while (true) {
					System.out.println("----------------------------------");
					System.out.println("1. Add Book" + "\n" + "2. View all books" + "\n" + "3. Delete Book" + "\n"
							+ "4. View all buying" + "\n" + "5. Logout" + "\n" + "-------------------------");

					int adminChoice = 0;
					while (true) {
						try {
							System.out.println("Enter Your choice :");
							adminChoice = sc.nextInt();
							break;

						} catch (InputMismatchException e) {
							sc.next();
							System.out.println("Entered data is invalid!!!! Please re-enter the field");
							System.out.println("--------------------------------------------------------");
						}
					}

					ManagerOperations mo = new ManagerOperations();

					switch (adminChoice) {
					case 1:
						Book bk = mo.addBook();
						String bname = db.addBook(bk);
						System.out.println("Book Added :" + bname);
						db.close();
						break;

					case 2:
						mo.displayBook();
						break;

					case 3:
						Book b = mo.deleteBook();
						String name = db.deleteBookDb(b);
						System.out.println("Book deleted :" + name);
						db.close();
						break;

					case 4:

						break;

					case 5:
						System.out.println("Logging out!!!!!!!!!");
						main(args);
						break;

					default:
						System.out.println("Please Select the valid option!!");
					}
				}
			} else {
				System.out.println("You are not the Manager!!!");
				System.out.println("Please login again");
			}
			break;
		case 2:// user
			int userChoice = 0;
			do {
				System.out.println("1. View all books" + "\n" + "2. Buy book" + "\n" + "3. Logout" + "\n"
						+ "-------------------------");
				userChoice = 0;
				while (true) {
					try {
						System.out.println("Enter your choice:");
						userChoice = sc.nextInt();
						break;
					} catch (InputMismatchException e) {
						sc.next();
						System.out.println("Entered data is invalid!!!! Please re-enter the field");
						System.out.println("--------------------------------------------------------");
					}
				}

				switch (userChoice) {
				case 1:

					break;
				case 2:

					break;
				case 3:
					main(args);
					break;

				default:
					System.out.println("Entered choice is invalid!!!");
				}
			} while (userChoice != 3);
			break;
		default:
			System.out.println("Invalid Option!!");
		}
		sc.close();

	}
}
