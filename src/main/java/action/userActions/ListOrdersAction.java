package action.userActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.interceptor.SessionAware;

import action.BaseAction;
import model.Book;
import model.Order;
import model.Orderitem;
import service.BookService;
import service.OrderService;
import service.OrderitemService;

public class ListOrdersAction extends BaseAction implements SessionAware {

	private static final long serialVersionUID = 1L;

	private Map<String, Object> session;
	private OrderService orderService;
	private OrderitemService orderitemService;
	private BookService bookService;
	
	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public OrderitemService getOrderitemService() {
		return orderitemService;
	}

	public void setOrderitemService(OrderitemService orderitemService) {
		this.orderitemService = orderitemService;
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

	public String execute() {
		int userId = (Integer) session.get("userId");
		List<Order> orders = orderService.getOrdersByUserId(userId);
		List<Book> bookList = bookService.getAllBooks();
		Map<Integer, List<Orderitem>> orderitems = new HashMap<Integer, List<Orderitem>>();
		for (int i = 0; i < orders.size(); i++) {
			Set<Integer> cur = orders.get(i).getOrderitems();
			List<Orderitem> oi = new ArrayList<Orderitem>();
			for (Integer j : cur) {
				oi.add(orderitemService.getOrderitemById(j));
			}
			orderitems.put(orders.get(i).getId(), oi);
		}
		Map<Long, String> books = new HashMap<Long, String>();
		if (bookList != null) {
			for (int i = 0; i < bookList.size(); i++) {
				Book book = bookList.get(i);
				books.put(book.getId(), book.getBook_name());
			}
		}
		request().setAttribute("orders", orders);
		request().setAttribute("orderitems", orderitems);
		request().setAttribute("books", books);
		return SUCCESS;
	}
}
