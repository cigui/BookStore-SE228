package model;

import java.io.Serializable;

public class ShoppingCartItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int user_id;
	private long book_id;
	private int number;
	
	public ShoppingCartItem() {
	}
	
	public ShoppingCartItem(int user_id, long book_id, int number) {
		this.user_id = user_id;
		this.book_id = book_id;
		this.number = number;
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public long getBook_id() {
		return book_id;
	}
	public void setBook_id(long book_id) {
		this.book_id = book_id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
}
