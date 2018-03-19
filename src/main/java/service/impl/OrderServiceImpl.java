package service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.OrderDao;
import model.Order;
import model.Orderitem;
import service.OrderService;

public class OrderServiceImpl implements OrderService{

	private OrderDao orderDao;
	
	public Integer addOrder(Order order) {
		return orderDao.save(order);
	}

	public void deleteOrder(Order order) {
		orderDao.delete(order);
	}

	public void updateOrder(Order order) {
		orderDao.update(order);
	}

	public Order getOrderById(int id) {
		return orderDao.getOrderById(id);
	}

	public List<Order> getAllOrders() {
		return orderDao.getAllOrders();
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public List<Order> getOrdersByUserId(int userId) {
		return orderDao.getOrdersByUserId(userId);
	}

	public List<Integer> getAllOrdersId() {
		return orderDao.getAllOrdersId();
	}

	@Override
	public List<Order> getOrdersOfUsers(int[] userId) {
		List<Order> result = new ArrayList<Order>();
		for (int i = 0; i < userId.length; i++) {
			result.addAll(orderDao.getOrdersByUserId(userId[i]));
		}
		Set<Order> set = new HashSet<Order>(result);
		result.clear();
		result.addAll(set);
		return result;
	}

	@Override
	public List<Order> getOrdersBetween(Date startingDate, Date endingDate) {
		return orderDao.getOrdersBetween(startingDate, endingDate);
	}

	@Override
	public List<Order> getOrdersOfItems(List<Orderitem> items) {
		List<Order> result = new ArrayList<Order>();
		for (Orderitem oi : items) {
			result.add(orderDao.getOrderById(oi.getOrder_id()));
		}
		Set<Order> set = new HashSet<Order>(result);
		result.clear();
		result.addAll(set);
		return result;
	}
}
