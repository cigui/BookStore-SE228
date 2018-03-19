package action.statisticsActions;

import action.BaseAction;
import service.BookService;
import service.UserService;

public class StatisticsInfoAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserService userService;
	private BookService bookService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public String execute() {
		request().setAttribute("categories", bookService.getAllCategories());
		request().setAttribute("users", userService.getAllUsers());
		return SUCCESS;
	}

}
