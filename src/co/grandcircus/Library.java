package co.grandcircus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library {

	public static List<Book> readBookInv(String fileName) {

		List<Book> bookList = new ArrayList<>();

		Path filePath = Paths.get(fileName);
		File f = filePath.toFile();
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(f));
			String line = br.readLine();

			while (line != null) {
				String[] arr = line.split(",");
				if (!arr[3].equals("null")) {
					Book book = new Book(arr[0], arr[1], checkedOut.valueOf(arr[2]), LocalDate.parse(arr[3]));
					bookList.add(book);
				} else {
				Book book = new Book(arr[0], arr[1], checkedOut.valueOf(arr[2]));
				bookList.add(book);
				}

				line = br.readLine();
			}

			br.close();

		} catch (FileNotFoundException e) {
			System.out.println("Book directory not found.");

		} catch (IOException e) {
			System.out.println("Something is wrong.");
		}
		return bookList;
	}

	public static void writeToFile(List<Book> list) {
		String fileName = "src/books.txt";
		Path path = Paths.get(fileName);

		// Path to.txt file
		File file = path.toFile();
		// initializing printwriter
		PrintWriter output = null;

		try {
			// should be false to overwrite past file
			output = new PrintWriter(new FileOutputStream(file, false));

			// takes list looks for book objects to put into
			for (Book b : list) {
				// System.out.println(b);
				output.println(b);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Cannot add to list.");
		} finally {
			// insurance - no more alterations to .txt
			output.close();
		}
	}

	public static void findByName(List<Book> list, String userSearchAuthor ) {
		Book b;
		int counter = 0;
		for (int i = 0; i < list.size(); i++) {
			
			String s = list.get(i).getAuthor();
			s = s.toLowerCase();
			userSearchAuthor = userSearchAuthor.toLowerCase();
			if(s.contains(userSearchAuthor)) {
				b = list.get(i);
				System.out.println("\n" + (i + 1) + ": " + b.getTitle() + " by " + b.getAuthor() + "\nStatus: " + b.isStatus() + "\nDue back on: " + b.getDueDate());
				counter++;
			}
			}
			if (counter == 0) {
				System.out.println("Book not found.");
			}
		}
		
	public static void findByTitle(List<Book> list, String userSearchTitle ) {
		Book b;
		int counter = 0;
		for (int i = 0; i < list.size(); i++) {
			
			String s = list.get(i).getTitle();
			s = s.toLowerCase();
			userSearchTitle = userSearchTitle.toLowerCase();
			if(s.contains(userSearchTitle)) {
				b = list.get(i);
				System.out.println("\n" + (i + 1) + ": " + b.getTitle() + " by " + b.getAuthor() + "\nStatus: " + b.isStatus() + "\nDue back on: " + b.getDueDate());
				counter++;
			}
			}
			if (counter == 0) {
				System.out.println("Book not found.");
			}
		}
	
	public static void addBook(Book book) {
		List<Book> b = readBookInv("src/books.txt");
		
		// adds book after checking for book title
		if(!b.contains(book.getTitle())) {
			b.add(book);
			writeToFile(b);
			
			// just in case book is not on file
		} else {
			System.out.println("This book is already on file.");
		}
		
	}
	
	
	
	
	
	
}
