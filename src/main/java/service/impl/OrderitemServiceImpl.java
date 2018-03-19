package service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.BookDao;
import dao.OrderDao;
import dao.OrderitemDao;
import model.Book;
import model.Order;
import model.Orderitem;
import service.OrderitemService;

public class OrderitemServiceImpl implements OrderitemService {

	private OrderitemDao orderitemDao;
	private BookDao bookDao;
	private OrderDao orderDao;
	
	public Integer addOrderitem(Orderitem orderitem) {
		int order_id = orderitem.getOrder_id();
		long book_id = orderitem.getBook_ISBN();
		int number = orderitem.getNumber();
		Book book = bookDao.getBookByISBN(book_id);
		if (book == null) return -1;
		int inv = book.getInventory();
		int pri = book.getBook_price();
		Order order = orderDao.getOrderById(order_id);
		int sum = order.getSum();
		if (inv - number >= 0) {
			orderitem.setUnit_price(pri);
			orderitem.setCategory(book.getBook_category());
			book.setInventory(inv-number);
			bookDao.update(book);
			order.setSum(sum+number*pri);
			orderDao.update(order);
			return orderitemDao.save(orderitem);
		}
		else {
			return -1;
		}
	}

	public void deleteOrderitem(Orderitem orderitem) {
		int order_id = orderitem.getOrder_id();
		long book_id = orderitem.getBook_ISBN();
		int number = orderitem.getNumber();
		int pri = orderitem.getUnit_price();
		Book book = bookDao.getBookByISBN(book_id);
		int inv = book.getInventory();
		Order order = orderDao.getOrderById(order_id);
		int sum = order.getSum();
		order.setSum(sum-number*pri);
		orderDao.update(order);
		book.setInventory(inv+number);
		bookDao.update(book);
		orderitemDao.delete(orderitem);
	}

	public void updateOrderitem(Orderitem orderitem) {
		/* Information */
		int order_id = orderitem.getOrder_id();
		long book_id = orderitem.getBook_ISBN();
		int number = orderitem.getNumber();
		Book book = bookDao.getBookByISBN(book_id);
		if (book == null) return;
		
		/* Old information */
		Orderitem old = orderitemDao.getOrderitemById(orderitem.getId());
		int oorder_id = old.getOrder_id();
		long obook_id = old.getBook_ISBN();
		int onumber = old.getNumber();
		int opri = old.getUnit_price();
		Book obook = bookDao.getBookByISBN(obook_id);
		int oinv = obook.getInventory();
		Order oorder = orderDao.getOrderById(oorder_id);
		int osum = oorder.getSum();
		
		/* Update book table */
		obook.setInventory(oinv + onumber);		
		bookDao.update(obook);
		book = bookDao.getBookByISBN(book_id);
		int pri = book.getBook_price();
		if (book.getInventory() - number < 0) {
			obook.setInventory(oinv - onumber);
			bookDao.update(obook);
			return;
		}
		book.setInventory(book.getInventory() - number);
		bookDao.update(book);
		
		/* Update order table */
		oorder.setSum(osum - onumber*opri);
		orderDao.update(oorder);
		Order order = orderDao.getOrderById(order_id);
		order.setSum(order.getSum() + number*pri);
		orderDao.update(order);
		
		orderitem.setUnit_price(pri);
		orderitem.setCategory(book.getBook_category());
		orderitemDao.update(orderitem);
	}

	public Orderitem getOrderitemById(int id) {
		return orderitemDao.getOrderitemById(id);
	}

	public List<Orderitem> getAllOrderitems() {
		return orderitemDao.getAllOrderitems();
	}

	public OrderitemDao getOrderitemDao() {
		return orderitemDao;
	}

	public void setOrderitemDao(OrderitemDao orderitemDao) {
		this.orderitemDao = orderitemDao;
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public List<Orderitem> getOrderitemsOfOrders(List<Order> orders) {
		List<Orderitem> result = new ArrayList<Orderitem>();
		for (Order order : orders) {
			result.addAll(orderitemDao.getOrderitemByOrderId(order.getId()));
		}
		return result;
	}

	@Override
	public List<Orderitem> getOrderitemsOfCategories(String[] categories) {
		Set<Orderitem> set = new HashSet<Orderitem>();
		for (int i = 0; i < categories.length; i++) {
			set.addAll(orderitemDao.getOrderitemByCategory(categories[i]));
		}
		List<Orderitem> result = new ArrayList<Orderitem>(set);
		return result;
	}
}
