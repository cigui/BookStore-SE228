package action;

import java.util.List;

import model.Book;
import service.BookService;

public class SearchBookAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BookService bookService;
	private String searchStr;
	
	public BookService getBookService() {
		return bookService;
	}
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	
	public String getSearchStr() {
		return searchStr;
	}
	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}
	public String execute() {
		
		List<Book> books = bookService.searchBook(searchStr);
		request().setAttribute("books", books);
		return SUCCESS;
	}
}
