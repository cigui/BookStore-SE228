package action;

import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

import model.BookCover;
import service.BookService;

public class GetCoverAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String isbn;
	private BookService bookService;
	private InputStream inputStream;
	private String fileContentType;

	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public InputStream getInputStream() {
		return inputStream;
	}
	
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String execute() {
		BookCover bookCover = bookService.getCover(Long.parseLong(isbn));
		setInputStream(bookCover.getInputStream());
		setFileContentType(bookCover.getContentType());
		return SUCCESS;
	}
}
