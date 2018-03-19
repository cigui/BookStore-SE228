package dao.impl;

import java.sql.Date;
import java.util.List;

import model.Order;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import dao.OrderDao;

@SuppressWarnings("deprecation")
public class OrderDaoImpl extends HibernateDaoSupport implements OrderDao {

	public Integer save(Order order) {
		return (Integer) getHibernateTemplate().save(order);
	}

	public void delete(Order order) {
		getHibernateTemplate().delete(order);
	}

	public void update(Order order) {
		getHibernateTemplate().merge(order);
	}

	public Order getOrderById(int id) {
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) getHibernateTemplate().find(
				"from Order as o where o.id=?", id);
		Order order = orders.size() > 0 ? orders.get(0) : null;
		return order;
	}

	public List<Order> getAllOrders() {
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) getHibernateTemplate().find(
				"from Order");
		return orders;
	}

	public List<Order> getOrdersByUserId(int userId) {
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) getHibernateTemplate().find(
				"from Order as o where o.user_id=?", userId);
		return orders;
	}

	@Override
	public List<Integer> getAllOrdersId() {
		@SuppressWarnings("unchecked")
		List<Integer> list = (List<Integer>) getHibernateTemplate()
				.find("select id from Order");
		return list;
	}

	@Override
	public List<Order> getOrdersBetween(Date startingDate, Date endingDate) {
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) getHibernateTemplate().find(
				"from Order as o where order_time between ? and ?", startingDate, endingDate);
		return orders;
	}

}
