package co.grandcircus;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class LibraryApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// display menu
//		ArrayList<Book> booklist = new ArrayList<>();
//		LocalDate currentDay = LocalDate.now();
//
//		Book a = new Book("Don Quixote", "Miguel de Cervantes", true);
//		Book b = new Book("ABCs", "alphabet", true);
//
//		booklist.add(a);
//		booklist.add(b);
//		// display books
//		for (int i = 0; i < booklist.size(); i++) {
//			System.out.println((i + 1) + ": " + booklist.get(i));
//		}
//
////		for (Book s : booklist) {
////			System.out.println(s);
////		}
//		// display books
//
//		// ask if they want to check out a book
//		System.out.println("would you like to search for a book or checkout a book?");
//		System.out.println("1: Search by Author");
//		System.out.println("2: Search by Title");
//		System.out.println("3: checkout a book");


		// dueDate = currentDay.plusDays(14)

		///////// new stuff

		Scanner scan = new Scanner(System.in);

		System.out.println("welcome to the Grand Circus library");
		System.out.println();
		int input;
		do {
		System.out.println("would you like to search for a book or checkout a book?");
		System.out.println("1: Display all books");
		System.out.println("2: Search by Author");
			System.out.println("3: Search by Title");
			System.out.println("4: checkout a book");
			System.out.println("5: leave");


		// if list books
			input = Validator.getInt(scan, "", 1, 5);
		if (input == 1) {

			List<Book> booklist = Library.readBookInv("src/books.txt");
			for (int i = 0; i < booklist.size(); i++) {
				System.out.println((i + 1) + ": " + booklist.get(i));
			}
		}
		// if search by author
		if (input == 2) {
			String authorName = Validator.getString(scan, "enter part or all of Author's name");

			Library.findByName(Library.readBookInv("src/books.txt"), authorName);

				// add search by name

		}
			// checkout a book
			if (input == 4) {
				LocalDate currentDay = LocalDate.now();
			System.out.println("which book do you want to checkout (enter the index #)");
				List<Book> booklist = Library.readBookInv("src/books.txt");
				int bookCheckOut = Validator.getInt(scan, "", 1, booklist.size());
				booklist.get(bookCheckOut - 1).setStatus(true);
				booklist.get(bookCheckOut - 1).setDueDate(currentDay.plusDays(14));

			}

			// exit condition
		} while (input != 5);

		System.out.println("bye!");
	}

}
