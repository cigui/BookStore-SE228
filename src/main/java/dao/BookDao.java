package dao;

import java.io.File;
import java.util.List;

import model.Book;
import model.BookCover;

public interface BookDao {

	public void save(Book book);

	public void delete(Book book);

	public void update(Book book);

	public Book getBookByISBN(long ISBN);

	public List<Book> getBooksByTitle(String title);
	
	public List<Book> getBooksByAuthor(String author);
	
	public List<Book> getAllBooks();

	public List<Long> getAllBooksIsbn();
	
	public boolean uploadCover(long ISBN, File file, String fileContentType, String fileFileName);
	
	public BookCover getCover(long ISBN);
}