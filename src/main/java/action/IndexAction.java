package action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Book;
import service.BookService;
public class IndexAction extends BaseAction{

	private static final long serialVersionUID = 1L;	
	
	private BookService bookService;

	public void setBookService(BookService appService) {
		this.bookService = appService;
	}
	
	public String execute() throws Exception {
		List<Book> bookList = bookService.getAllBooks();
		Map<Long, String> books = new HashMap<Long, String>();
		if (bookList != null) {
			for (int i = 0; i < bookList.size(); i++) {
				Book book = bookList.get(i);
				books.put(book.getId(), book.getBook_name());
			}
			request().setAttribute("books", books);
		}
		return SUCCESS;
	}
}
