package co.grandcircus;

import java.util.ArrayList;
import java.util.List;

public class Test_BookMain {

	public static void main(String[] args) {

		
	Library.readBookInv("src/books.txt");
	Book book = new Book("Charlotte's Web", "Author", true, null);
		
	Library.addBook(Library.readBookInv("src/books.txt"), book);	
	}

}
