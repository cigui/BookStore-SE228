package model;

public class Orderitem {

	private int id;
	private int order_id;
	private long book_ISBN;
	private int number;
	private int unit_price;
	private String category;

	public boolean equals(Object o) {
		if (this == o){
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;	
		}
		if (id != ((Orderitem)o).id){
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = ((Integer)id).hashCode();
		return result;
	}

	public Orderitem() {
	}

	public Orderitem(int orderid, long ISBN, int amount) {
		this.setOrder_id(orderid);
		this.setBook_ISBN(ISBN);
		this.setNumber(amount);
		this.setUnit_price(0);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public long getBook_ISBN() {
		return book_ISBN;
	}

	public void setBook_ISBN(long book_ISBN) {
		this.book_ISBN = book_ISBN;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(int unit_price) {
		this.unit_price = unit_price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	

/*	private Order order;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
*/
}
