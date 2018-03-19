package action.shoppingCartActions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import action.BaseAction;
import model.ShoppingCartItem;
import service.BookService;
import service.ShoppingCartService;

public class ListShoppingCartAction extends BaseAction implements SessionAware {
	private static final long serialVersionUID = 1L;

	private Map<String, Object> session;
	private ShoppingCartService shoppingCartService;
	private BookService bookService;
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
	public ShoppingCartService getShoppingCartService() {
		return shoppingCartService;
	}
	public void setShoppingCartService(ShoppingCartService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}
	
	public BookService getBookService() {
		return bookService;
	}
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	public String execute() {
		try {
			int userId = (Integer) session.get("userId");
			List<ShoppingCartItem> cart = shoppingCartService.getShoppingCartOfUser(userId);
			Map<Long, String> books = new HashMap<Long, String>();
			for (int i = 0; i < cart.size(); i++) {
				long isbn = cart.get(i).getBook_id();
				books.put(isbn,bookService.getBookByISBN(isbn).getBook_name());
			}
			request().setAttribute("cart", cart);
			request().setAttribute("cartBooks", books);
			return SUCCESS;
		}
		catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
}
