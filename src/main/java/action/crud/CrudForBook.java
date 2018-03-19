package action.crud;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import action.BaseAction;
import model.Book;
import service.BookService;
import utils.JsonBean;

public class CrudForBook extends BaseAction {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> resultMap;
	private List<JsonBean> resultList;
	private BookService bookService;
	private long id;
	private String book_name;
	private String book_author;
	private int book_price;
	private String book_publisher;
	private String book_category;
	private int inventory;

	private long isbn;
	private File file; 
	private String fileContentType;
	private String fileFileName;

	public long getId() {
		return id;
	}
	public void setId(long iSBN) {
		id = iSBN;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getBook_author() {
		return book_author;
	}
	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}
	public int getBook_price() {
		return book_price;
	}
	public void setBook_price(int book_price) {
		this.book_price = book_price;
	}
	public String getBook_publisher() {
		return book_publisher;
	}
	public void setBook_publisher(String book_publisher) {
		this.book_publisher = book_publisher;
	}
	public String getBook_category() {
		return book_category;
	}
	public void setBook_category(String book_version) {
		this.book_category = book_version;
	}
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	public void setBookService(BookService appService) {
		this.bookService = appService;
	}
	public void setIsbn(long ISBN) {
		isbn = ISBN;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public Map<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	public List<JsonBean> getResultList() {
		return resultList;
	}
	public void setResultList(List<JsonBean> resultList) {
		this.resultList = resultList;
	}
	
	public String All() throws Exception {
		List<Book> books = bookService.getAllBooks();
		resultMap = new HashMap<String, Object>(); 
		resultMap.put("total", books.size());  
		resultMap.put("rows", books);
		return "all";
	}
	
	public String Add() throws Exception {
		Book book = new Book(id, book_name, book_author, book_price, book_publisher, book_category, inventory);
		bookService.addBook(book);
		resultMap = new HashMap<String, Object>(); 
		resultMap.put("success", true);
		return "add";
	}

	public String Delete() throws Exception {
		Book book = bookService.getBookByISBN(id);
		bookService.deleteBook(book);
		resultMap = new HashMap<String, Object>(); 
		resultMap.put("success", true);
		return "delete";
	}

	public String Update() throws Exception {
		Book book = bookService.getBookByISBN(id);
		book.setBook_author(book_author);
		book.setBook_name(book_name);
		book.setBook_price(book_price);
		book.setBook_publisher(book_publisher);
		book.setBook_category(book_category);
		book.setInventory(inventory);
		bookService.updateBook(book);
		resultMap = new HashMap<String, Object>(); 
		resultMap.put("success", true);
		return "update";
	}

	public String IsbnList() throws Exception {
		List<Long> books = bookService.getAllBooksIsbn();
		resultList = new ArrayList<JsonBean>();
		for (int i = 0; i < books.size(); i++) {
			JsonBean tmp = new JsonBean(books.get(i).toString(), books.get(i).toString());
			resultList.add(tmp);
		}
		return "isbnList";
	}
	
	public String UploadCover() throws Exception {
		bookService.uploadCover(isbn, file, fileContentType, fileFileName);
		resultMap.put("success", true);
		return "upload";
	}
}
