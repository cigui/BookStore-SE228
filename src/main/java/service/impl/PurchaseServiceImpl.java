package service.impl;

import dao.BookDao;
import dao.OrderDao;
import dao.OrderitemDao;
import dao.UserDao;
import model.Book;
import model.Order;
import model.Orderitem;
import service.PurchaseService;

public class PurchaseServiceImpl implements PurchaseService{

	private UserDao userDao;
	private BookDao bookDao;
	private OrderDao orderDao;
	private OrderitemDao orderitemDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
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

	public OrderitemDao getOrderitemDao() {
		return orderitemDao;
	}

	public void setOrderitemDao(OrderitemDao orderitemDao) {
		this.orderitemDao = orderitemDao;
	}

	public int placeOrder(int userId, long bookId, int number) {
		try {
			if (userDao.getUserById(userId) == null) {
				return -2;
			}
			Book book = bookDao.getBookByISBN(bookId);
			if (book == null) {
				return -3;
			}
			int inv = book.getInventory();
			int unit_price = book.getBook_price();
			if (inv < number) {
				return -4;
			}
			book.setInventory(inv-number);
			bookDao.update(book);
			Order order = new Order(userId);
			int sum = (unit_price * number);
			order.setSum(sum);
			int orderId = orderDao.save(order);
			Orderitem oi = new Orderitem(orderId, bookId, number);
			oi.setCategory(book.getBook_category());
			oi.setUnit_price(unit_price);
			orderitemDao.save(oi);
			return sum;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	
	
}
