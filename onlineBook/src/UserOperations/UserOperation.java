package UserOperations;

import java.util.List;
import java.util.Scanner;

import onlineBook.Book;
import onlineBook.DbOperations;

public class UserOperation implements UserInterface {
	Scanner ab = new Scanner(System.in);
	DbOperations db = new DbOperations();

	@Override
	public void displayBook() {
		List<Book> list = db.displayBook();
		System.out.println("------------------------------------");
		System.out.println("Displaying the books from database :");
		System.out.println("------------------------------------");
		if (list != null) {
			for (Book l : list) {
				System.out.println("Book name             	:" + l.getBookname());
				System.out.println("Book Id               	:" + l.getBookid());
				System.out.println("Book Cost             	:" + l.getBookcost());
				System.out.println("Book Copies Available 	:" + l.getCopies());
				System.out.println("Book Author Name	:" + l.getAuthor());
				System.out.println("Book Cost		:" + l.getBookcost());
				System.out.println("----------**********----------");
			}
		} else {
			System.out.println("No Books available available!!!!!!!");

		}
	}

	public boolean BuyBook() {
		String name = null;
		System.out.println("Enter your name");
		name = ab.next();
		while (!name.matches("[a-zA-Z]+")) {
			System.out.println("Please Enter the valid name :");
			name = ab.next();
		}
		String mob = null;
		System.out.println("Enter your mobile number :");
		mob = ab.next();
		while (!mob.matches("[0-9]{10}")) {
			System.out.println("Please Enter the valid Contact number :");
			mob = ab.next();
		}

		System.out.println("Enter your mail ID:");
		String mail = ab.next();

		System.out.println("--------------------------");
		System.out.println("Enter the book name :");
		String bname = ab.next();
		while (!bname.matches("[a-zA-Z]+")) {
			System.out.println("Please Enter the valid book name :");
			bname = ab.next();
		}

		if (db.displayBooksingle(bname)) {
			System.out.println("Enter the number of copies for buying :");
			if (db.displayCopies(bname)) {
				int copy = ab.nextInt();
				System.out.println("Payment method :" + "----------------" + "\n" + "Cash" + "\n" + "Card" + "\n"
						+ "Online payment");
				System.out.println("----------------");
				String pay = ab.next();
				System.out.println("-------------------------------");
				buyer by = new buyer(name, mob, mail, bname, copy, pay);
				boolean status = db.addBuyer(by);
				if (status) {
					System.out.println("Buying book successfully");
					return true;
				} else {
					System.out.println("Buying book not successfully");
				}
			}
			return false;
		}
		return false;
	}

}
