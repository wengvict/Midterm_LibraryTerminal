package co.grandcircus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		// list for books to be checked out
		List<Book> checkedOutBooks = new ArrayList<Book>();

		System.out.println("welcome to the Grand Circus library");

		int input;
		do {
			System.out.println();
			System.out.println("would you like to search for a book or checkout a book?");
			System.out.println("1: Display all books");
			System.out.println("2: Search by Author");
			System.out.println("3: Search by Title");
			System.out.println("4: Checkout a book");
			System.out.println("5: Returna book");
			System.out.println("6: Exit");

			// if 1. list books
			input = Validator.getInt(scan, "", 1, 5);
			if (input == 1) {

				List<Book> booklist = Library.readBookInv("src/books.txt");

				for (int i = 0; i < booklist.size(); i++) {
					Book b = booklist.get(i);
					String authorF = String.format("| %-25s | ", b.getAuthor());
					String titleF = String.format(" %-43s| ", b.getTitle());
					String onShelfF = String.format(" %-3s %n", b.isStatus());
					String index = String.format(" %-5s ", (i + 1) + ": ");

					System.out.println(index + authorF + titleF + onShelfF);
				}
			}
			// if 2. search by author
			if (input == 2) {
				String authorName = Validator.getString(scan, "enter part or all of Author's name");

				Library.findByName(Library.readBookInv("src/books.txt"), authorName);
			}
			// add search by name
			if (input == 3) {
				String userTitle = Validator.getString(scan, "enter part or all of book title");
				Library.findByTitle(Library.readBookInv("src/books.txt"), userTitle);
			}

			// checkout a book
			if (input == 4) {
				LocalDate currentDay = LocalDate.now();
				System.out.println("which book do you want to checkout (enter the index #)");
				List<Book> booklist = Library.readBookInv("src/books.txt");
				int bookCheckOut = Validator.getInt(scan, "", 1, booklist.size());

				if ((booklist.get(bookCheckOut - 1).isStatus()) == checkedOut.ON_SHELF) {
					// check if book is already checkout out. if onshelf=true, continue
					System.out.print("you checked out: ");
					System.out.println(booklist.get(bookCheckOut - 1).getTitle() + " by "
							+ booklist.get(bookCheckOut - 1).getAuthor());
					booklist.get(bookCheckOut - 1).setStatus(checkedOut.CHECKED_OUT);
					booklist.get(bookCheckOut - 1).setDueDate(currentDay.plusDays(14));
					System.out.println("due date: " + booklist.get(bookCheckOut - 1).getDueDate());

					checkedOutBooks.add(booklist.get(bookCheckOut - 1));
					// add updated book to list
					// make new list of checked out books
					Library.writeToFile(booklist);

				} else {
					System.out.println("That book is already checked out");
					List<Book> booklist1 = Library.readBookInv("src/books.txt");
					System.out.println(booklist.get((bookCheckOut) - 1).getTitle() + " is due back on "
							+ booklist1.get(bookCheckOut - 1).getDueDate());
				}

				//

			}
//
			if (input == 5) {
				List<Book> booklist = Library.readBookInv("src/books.txt");

				int counter = 0;

				for (int i = 0; i < booklist.size(); i++) {

					if (booklist.get(i).isStatus() == checkedOut.CHECKED_OUT) {

						counter++;

						Book b = booklist.get(i);
						String authorF = String.format("| %-25s | ", b.getAuthor());
						String titleF = String.format(" %-43s| ", b.getTitle());
						String onShelfF = String.format(" %-3s ", b.isStatus());
						String index = String.format(" %-5s ", (i + 1) + ": ");
						String dateF = String.format("| %-8s %n", b.getDueDate());
						
						System.out.println(index + authorF + titleF + onShelfF + dateF);
					}

				}
				if (counter > 0) {
					System.out.println("Which book are you returning? ");

					//
					int returnBook = Validator.getInt(scan, "");
					booklist.get(returnBook - 1).setStatus(checkedOut.ON_SHELF);
					Library.writeToFile(booklist);
				} else {
					System.out.println("There are no books checked out to return.");
				}
			}

			// exit condition
		} while (input != 6);

		// display books that were checked out

		if (checkedOutBooks.size() > 0) {
			System.out.println("you checked out:");
			for (Book a : checkedOutBooks) {
				System.out.println(a.getTitle() + ", due: " + a.getDueDate());
			}
		}
		System.out.println();
		System.out.println("bye!");
	}

}
