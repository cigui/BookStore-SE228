package model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Order {

	private int id;
	private int user_id;
	private Timestamp order_time;
	private int sum;

	@Override
	public boolean equals(Object o) {
		if (this == o){
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;	
		}
		if (id != ((Order)o).id){
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = ((Integer)id).hashCode();
		return result;
	}

	public Order() {
	}

	public Order(int userid) {
		this.setUser_id(userid);
		this.setSum(0);
	}

	public Order(int userid, Timestamp date) {
		this.setUser_id(userid);
		this.setOrder_time(date);
		this.setSum(0);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public Timestamp getOrder_time() {
		return order_time;
	}

	public void setOrder_time(Timestamp order_time) {
		this.order_time = order_time;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	private Set<Integer> orderitems = new HashSet<Integer>();

	public Set<Integer> getOrderitems() {
		return orderitems;
	}

	public void setOrderitems(Set<Integer> orderitems) {
		this.orderitems = orderitems;
	}
}
