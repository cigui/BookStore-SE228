package service.impl;

import java.util.ArrayList;
import java.util.List;

import dao.BookDao;
import dao.OrderDao;
import dao.OrderitemDao;
import dao.ShoppingCartItemDao;
import model.Book;
import model.Order;
import model.Orderitem;
import model.ShoppingCartItem;
import service.ShoppingCartService;

public class ShoppingCartServiceImpl implements ShoppingCartService{
	private ShoppingCartItemDao shoppingCartItemDao;
	private OrderDao orderDao;
	private OrderitemDao orderitemDao;
	private BookDao bookDao;

	public ShoppingCartItemDao getShoppingCartItemDao() {
		return shoppingCartItemDao;
	}

	public void setShoppingCartItemDao(ShoppingCartItemDao shoppingCartItemDao) {
		this.shoppingCartItemDao = shoppingCartItemDao;
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

	public void setOrderitemDao(OrderitemDao orderItemDao) {
		this.orderitemDao = orderItemDao;
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public List<ShoppingCartItem> getShoppingCartOfUser(int UserId) {
		return shoppingCartItemDao.getShoppingItemsByUserId(UserId);
	}

	@Override
	public void addIntoShoppingCart(ShoppingCartItem item) {
		shoppingCartItemDao.save(item);
	}

	@Override
	public void deleteFromShoppingCart(ShoppingCartItem item) {
		shoppingCartItemDao.delete(item);
	}

	private boolean addIntoOrder(ShoppingCartItem item, Order order, List<Orderitem> ois) {
		/* check the number and the inventory */
		long isbn = item.getBook_id();
		int number = item.getNumber();
		Book book = bookDao.getBookByISBN(isbn);
		if (number <= book.getInventory()) {
			Orderitem oi = new Orderitem();
			oi.setOrder_id(order.getId());
			oi.setBook_ISBN(isbn);
			oi.setNumber(number);
			oi.setUnit_price(bookDao.getBookByISBN(isbn).getBook_price());
			oi.setCategory(bookDao.getBookByISBN(isbn).getBook_category());
			orderitemDao.save(oi);
			ois.add(oi);
			book.setInventory(book.getInventory() - number);
			bookDao.update(book);
			shoppingCartItemDao.delete(item);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean purchase(List<ShoppingCartItem> items) {
		int i = 0;
		boolean result = true;
		int userId = items.get(0).getUser_id();
		Order order = new Order(userId);
		List<Orderitem> ois = new ArrayList<Orderitem>();
		orderDao.save(order);
		for (i = 0; i < items.size(); i++) {
			ShoppingCartItem item = items.get(i);
			if (!addIntoOrder(item, order, ois)) {
				result = false;
			}
		}
		
		/* calculate the sum for the order and update it */
		int sum = 0;
		for (i = 0; i < ois.size(); i++) {
			sum += ois.get(i).getUnit_price() * ois.get(i).getNumber();
		}
		order.setSum(sum);
		orderDao.update(order);
		
		return result;
	}

	@Override
	public ShoppingCartItem getItemByKey(int userId, long isbn) {
		return shoppingCartItemDao.get(userId, isbn);
	}

	@Override
	public void updateItemInfo(ShoppingCartItem item) {
		shoppingCartItemDao.update(item);
	}
}
