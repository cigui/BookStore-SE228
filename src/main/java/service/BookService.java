package service;

import java.io.File;
import java.util.List;
import java.util.Set;

import model.Book;
import model.BookCover;

public interface BookService {
	public void addBook(Book book);

	public void deleteBook(Book book);

	public void updateBook(Book book);

	public Book getBookByISBN(long ISBN);

	public List<Book> getAllBooks();
	
	public Set<String> getAllCategories();
	
	public List<Long> getAllBooksIsbn();
	
	public boolean uploadCover(long ISBN, File file, String fileContentType, String fileFileName);
	
	public List<Book> searchBook(String inputString);
	
	public BookCover getCover(long isbn);
}
