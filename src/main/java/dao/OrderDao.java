package dao;

import java.sql.Date;
import java.util.List;

import model.Order;

public interface OrderDao {

	public Integer save(Order order);

	public void delete(Order order);

	public void update(Order order);

	public Order getOrderById(int id);

	public List<Order> getAllOrders();

	public List<Integer> getAllOrdersId();
	
	public List<Order> getOrdersByUserId(int userId);
	
	public List<Order> getOrdersBetween(Date startingDate, Date endingDate);
}