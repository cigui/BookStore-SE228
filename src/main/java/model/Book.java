package model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Book {

	@Id ObjectId databaseId;
	private long id;
	private String book_name;
	private String book_author;
	private int book_price;
	private String book_publisher;
	private String book_category;
	private int inventory;

	public Book() {
		this.id = 0;
		this.book_name = null;
		this.book_author = null;
		this.book_price = 0;
		this.book_publisher = null;
		this.book_category = null;
		this.inventory = 0;
	}

	public Book(long ISBN, String name, String author, int price, String publisher, 
			String book_category, int inv){
		this.id = ISBN;
		this.book_name = name;
		this.book_author = author;
		this.book_price = price;
		this.book_publisher = publisher;
		this.book_category = book_category;
		this.inventory = inv;
	}

	public long getId() {
		return id;
	}

	public void setId(long isbn) {
		this.id = isbn;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBook_author() {
		return book_author;
	}

	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}

	public int getBook_price() {
		return book_price;
	}

	public void setBook_price(int book_price) {
		this.book_price = book_price;
	}

	public String getBook_publisher() {
		return book_publisher;
	}

	public void setBook_publisher(String book_publisher) {
		this.book_publisher = book_publisher;
	}

	public String getBook_category() {
		return book_category;
	}

	public void setBook_category(String book_category) {
		this.book_category = book_category;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	
}
