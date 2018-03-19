package action;

import java.util.HashMap;
import java.util.Map;

import model.Book;
import service.BookService;

public class BookInfoAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private long ISBN;
	private BookService bookService;
	private Map<String, String> resultMap;
	
	public long getISBN() {
		return ISBN;
	}

	public void setISBN(long id) {
		this.ISBN = id;
	}
	
	public Map<String, String> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, String> resultMap) {
		this.resultMap = resultMap;
	}

	public void setBookService(BookService appService) {
		this.bookService = appService;
	}
	
	public String execute() throws Exception {
		Book book = bookService.getBookByISBN(ISBN);
		setResultMap(new HashMap<String, String>());
		resultMap.put("ISBN", Long.toString(book.getId()));
		resultMap.put("title", book.getBook_name());
		resultMap.put("publisher", book.getBook_publisher());
		resultMap.put("author", book.getBook_author());
		resultMap.put("category", book.getBook_category());
		resultMap.put("price", Integer.toString(book.getBook_price()));
		resultMap.put("inventory", Integer.toString(book.getInventory()));
		return SUCCESS;
	}
}
