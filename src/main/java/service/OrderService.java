package service;

import java.sql.Date;
import java.util.List;

import model.Order;
import model.Orderitem;

public interface OrderService {
	public Integer addOrder(Order order);

	public void deleteOrder(Order order);

	public void updateOrder(Order order);

	public Order getOrderById(int id);

	public List<Order> getAllOrders();
	
	public List<Integer> getAllOrdersId();
	
	public List<Order> getOrdersByUserId(int userId);
	
	public List<Order> getOrdersOfUsers(int userId[]);
	
	public List<Order> getOrdersOfItems(List<Orderitem> items);
	
	public List<Order> getOrdersBetween(Date startingDate, Date endingDate);
	
}
