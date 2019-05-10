package co.grandcircus;

import java.time.LocalDate;

public class Book {

	private String title;
	private String author;
	private checkedOut onShelf;
	private LocalDate dueDate;
	
	public Book() {
		
	}
	
	public Book(String title, String author, checkedOut onShelf, LocalDate dueDate) {
		this.title = title;
		this.author = author;
		this.onShelf = onShelf;
		this.dueDate = dueDate;
	}
	
	// this overloaded cnstructor takes no dueDate
	// if book has no dueDate, add if/else statement for onShelf in library class
	public Book(String title, String author, checkedOut onShelf) {
		this.title = title;
		this.author = author;
		this.onShelf = onShelf;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public checkedOut isStatus() {
		return onShelf;
	}
	public void setStatus(checkedOut onShelf) {
		this.onShelf = onShelf;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {
		return title + "," + author + "," + onShelf + "," + dueDate;
	}
	
	
	
}
