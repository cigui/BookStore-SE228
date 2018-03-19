package service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.xwork.StringUtils;

import dao.BookDao;
import model.Book;
import model.BookCover;
import service.BookService;

public class BookServiceImpl implements BookService{
	
	private BookDao bookDao;

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	
	public void addBook(Book book) {
		bookDao.save(book);
	}

	public void deleteBook(Book book) {
		bookDao.delete(book);
	}

	public void updateBook(Book book) {
		bookDao.update(book);
	}

	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}

	public Set<String> getAllCategories() {
		Set<String> result = new HashSet<String>();
		List<Book> books = bookDao.getAllBooks();
		for (Book book : books) {
			result.add(book.getBook_category());
		}
		return result;
	}
	
	public Book getBookByISBN(long ISBN) {
		return bookDao.getBookByISBN(ISBN);
	}

	@Override
	public List<Long> getAllBooksIsbn() {
		return bookDao.getAllBooksIsbn();
	}

	@Override
	public boolean uploadCover(long ISBN, File file, String fileContentType, String fileFileName) {
		return bookDao.uploadCover(ISBN, file, fileContentType, fileFileName);
	}

	@Override
	public BookCover getCover(long isbn) {
		return bookDao.getCover(isbn);
	}

	@Override
	public List<Book> searchBook(String inputString) {
		List<Book> result = new ArrayList<Book>();
		if (StringUtils.isNumeric(inputString)){
			Book book = bookDao.getBookByISBN(Long.parseLong(inputString));
			if (book != null) result.add(book);
		}
		result.addAll(bookDao.getBooksByTitle(inputString));
		result.addAll(bookDao.getBooksByAuthor(inputString));
		return result;
	}
	
	
}
