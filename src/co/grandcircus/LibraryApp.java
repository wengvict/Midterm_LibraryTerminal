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

		System.out.println("Welcome to the Grand Circus Library!");

		int input;
		do {
			System.out.println();
			System.out.println("Would you like to search for a book or checkout a book?\n");
			System.out.println("1: Display All Books");
			System.out.println("2: Search by Author");
			System.out.println("3: Search by Title");
			System.out.println("4: Checkout a Book");
			System.out.println("5: Return a Book");
			System.out.println("6: Add a Book");
			System.out.println("7: Exit");
			
			System.out.print("\nPick a menu option: ");

			// if 1. list books
			input = Validator.getInt(scan, "", 1, 6);
			if (input == 1) {

				List<Book> booklist = Library.readBookInv("src/books.txt");

				for (int i = 0; i < booklist.size(); i++) {
					Book b = booklist.get(i);
					String authorF = String.format("| %-25s | ", b.getAuthor());
					String titleF = String.format(" %-43s| ", b.getTitle());
					String onShelfF = "";
					if (b.isStatus() == checkedOut.CHECKED_OUT) {
						onShelfF = String.format(" %-3s %n", "Checked out");
					}
					if (b.isStatus() == checkedOut.ON_SHELF) {
						onShelfF = String.format(" %-3s %n", "On Shelf");
					}
					String index = String.format(" %-5s ", (i + 1) + ": ");

					System.out.println(index + authorF + titleF + onShelfF);
				}
			}
			// if 2. search by author
			if (input == 2) {
				String authorName = Validator.getString(scan, "Enter part or all of author's name: ");

				Library.findByName(Library.readBookInv("src/books.txt"), authorName);
			}
			// add search by name
			if (input == 3) {
				String userTitle = Validator.getString(scan, "Enter part or all of book title: ");
				Library.findByTitle(Library.readBookInv("src/books.txt"), userTitle);
			}

			// checkout a book
			if (input == 4) {
				LocalDate currentDay = LocalDate.now();
				System.out.print("Enter the index number of the book you want to checkout: ");
				List<Book> booklist = Library.readBookInv("src/books.txt");
				int bookCheckOut = Validator.getInt(scan, "", 1, booklist.size());

				if ((booklist.get(bookCheckOut - 1).isStatus()) == checkedOut.ON_SHELF) {
					// check if book is already checkout out. if onshelf=true, continue
					System.out.print("\nYou checked out: ");
					System.out.println(booklist.get(bookCheckOut - 1).getTitle() + " by "
							+ booklist.get(bookCheckOut - 1).getAuthor());
					booklist.get(bookCheckOut - 1).setStatus(checkedOut.CHECKED_OUT);
					booklist.get(bookCheckOut - 1).setDueDate(currentDay.plusDays(14));
					System.out.println("Due back on: " + booklist.get(bookCheckOut - 1).getDueDate());

					checkedOutBooks.add(booklist.get(bookCheckOut - 1));
					// add updated book to list
					// make new list of checked out books
					Library.writeToFile(booklist);

				} else {
					System.out.println("\nThat book is already checked out.");
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
						
						System.out.println("\n"+ index + authorF + titleF + onShelfF + dateF);
					}

				}
				if (counter > 0) {
					System.out.println("Enter any book number that is not on the list to return to the menu.");
					System.out.print("Enter the index number of the book you are returning: ");

					//
					int returnBook = Validator.getInt(scan, "", 1, booklist.size());
					booklist.get(returnBook - 1).setStatus(checkedOut.ON_SHELF);
					booklist.get(returnBook - 1).setDueDate(null);
					Library.writeToFile(booklist);
				} else {
					System.out.println("No books are currently checked out.");
				}
			}

			// adds book
			if (input == 6) {
				System.out.println("Add a Book\n");
				System.out.print("Book title: ");
				System.out.print("\n");
				String addBookTitle = scan.nextLine();
				System.out.print("Author name: ");
				String addAuthorName = scan.nextLine();
				
				Book book = new Book(addBookTitle, addAuthorName, checkedOut.ON_SHELF);
				
				Library.addBook(book);
			
			}
		// exit condition
		} while (input != 7);

		// display books that were checked out

		if (checkedOutBooks.size() > 0) {
			System.out.println("You checked out: ");
			for (Book a : checkedOutBooks) {
				System.out.println(a.getTitle() + ", due: " + a.getDueDate());
			}
		}
		System.out.println();
		System.out.println("Thank you for stopping by the Grand Circus Library!");
	}

}
