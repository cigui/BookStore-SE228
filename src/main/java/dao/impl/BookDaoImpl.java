package dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import  org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import static org.springframework.data.mongodb.core.query.Query.query;

import model.Book;
import model.BookCover;
import dao.BookDao;

public class BookDaoImpl implements BookDao {
	
	private MongoTemplate mongoTemplate;
	private GridFsTemplate gridFsTemplate;

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	public void setGridFsTemplate(GridFsTemplate gridFsTemplate) {
		this.gridFsTemplate = gridFsTemplate;
	}

	public void save(Book book) {
		try {
			mongoTemplate.insert(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(Book book) {
		try {
			mongoTemplate.remove(book);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(Book book) {
		try {
			mongoTemplate.updateFirst(query(where("id").is(book.getId())), new Update().set("book_name", book.getBook_name()), Book.class);
			mongoTemplate.updateFirst(query(where("id").is(book.getId())), new Update().set("book_price", book.getBook_price()), Book.class);
			mongoTemplate.updateFirst(query(where("id").is(book.getId())), new Update().set("book_publisher", book.getBook_publisher()), Book.class);
			mongoTemplate.updateFirst(query(where("id").is(book.getId())), new Update().set("book_category", book.getBook_category()), Book.class);
			mongoTemplate.updateFirst(query(where("id").is(book.getId())), new Update().set("book_author", book.getBook_author()), Book.class);
			mongoTemplate.updateFirst(query(where("id").is(book.getId())), new Update().set("inventory", book.getInventory()), Book.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Book getBookByISBN(long ISBN) {
		try {
			return (Book) mongoTemplate.findOne(query(where("id").is(ISBN)), Book.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Book> getAllBooks() {
		try {
			return mongoTemplate.findAll(Book.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}     
	}

	@Override
	public List<Long> getAllBooksIsbn() {
		try {
			List<Book> books = mongoTemplate.findAll(Book.class);
			List<Long> result = new ArrayList<Long>();
			for (Book i:books) {
				result.add(i.getId());
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}

	@Override
	public boolean uploadCover(long ISBN, File file, String fileContentType, String fileFileName) {
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			DBObject metadata = new BasicDBObject("isbn", ISBN);
			GridFSFile oldFile = gridFsTemplate.findOne(new Query(Criteria.where("metadata.isbn").is(ISBN)));
			if (oldFile != null) {
				gridFsTemplate.delete(new Query(Criteria.where("metadata.isbn").is(ISBN)));
			}
			gridFsTemplate.store(fileInputStream, fileFileName, fileContentType, metadata);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public BookCover getCover(long ISBN) {
		BookCover result = new BookCover();
		
		// Get the file
		GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("metadata.isbn").is(ISBN)));
		if (file == null) {
			file = gridFsTemplate.findOne(new Query(Criteria.where("filename").is("404.png")));
		}
		
		// Get the resource from the file
		InputStream downloadStream = GridFSBuckets.create(mongoTemplate.getDb()).openDownloadStream(file.getObjectId()); 
		GridFsResource resource = new GridFsResource(file, downloadStream);
		
		result.setContentType(resource.getContentType());
		try {
			result.setInputStream(resource.getInputStream());
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Book> getBooksByTitle(String title) {
		try {
			return (List<Book>) mongoTemplate.find(query(where("book_name").regex(title)), Book.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Book> getBooksByAuthor(String author) {
		try {
			return (List<Book>) mongoTemplate.find(query(where("book_author").regex(author)), Book.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
