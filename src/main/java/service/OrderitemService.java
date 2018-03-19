package service;

import java.util.List;

import model.Order;
import model.Orderitem;

public interface OrderitemService {
	public Integer addOrderitem(Orderitem orderitem);

	public void deleteOrderitem(Orderitem orderitem);

	public void updateOrderitem(Orderitem orderitem);

	public Orderitem getOrderitemById(int id);

	public List<Orderitem> getAllOrderitems();

	public List<Orderitem> getOrderitemsOfOrders(List<Order> orders);
	
	public List<Orderitem> getOrderitemsOfCategories(String[] categories);
}
