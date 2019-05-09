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
				Book book = new Book(arr[0], arr[1], Boolean.parseBoolean(arr[2]));
				bookList.add(book);
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

	public static void addBook(List<Book> list) {
		String fileName = "books.txt";
		Path path = Paths.get(fileName);

		// Path to.txt file
		File file = path.toFile();
		// initializing printwriter
		PrintWriter output = null;

		try {
			output = new PrintWriter(new FileOutputStream(file, true));

			// takes list looks for book objects to put into
			for (Book b : list) {
				output.println(b);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Cant add to list");
		} finally {
			// insurance - no more alterations to .txt
			output.close();
		}
	}

	public static void findByName(List<Book> list, String userSearchAuthor ) {
		int counter = 0;
		for (int i = 0; i < list.size(); i++) {
			
			
			String s = list.get(i).getAuthor();
			s = s.toLowerCase();
			userSearchAuthor = userSearchAuthor.toLowerCase();
			if(s.contains(userSearchAuthor)) {
				System.out.println((i + 1) + ": " + list.get(i));
				counter++;
			}
			}
			if (counter == 0) {
				System.out.println("not found");
			}
		}
		
	public static void findByTitle(List<Book> list, String userSearchTitle ) {
		int counter = 0;
		for (int i = 0; i < list.size(); i++) {
			
			String s = list.get(i).getAuthor();
			s = s.toLowerCase();
			userSearchTitle = userSearchTitle.toLowerCase();
			if(s.contains(userSearchTitle)) {
				System.out.println((i + 1) + ": " + list.get(i));
				counter++;
			}
			}
			if (counter == 0) {
				System.out.println("not found");
			}
		}


}
