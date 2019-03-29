//Author: Tonny Huang

package com.st.lms.service;

import java.util.List;

import com.st.lms.dao.*;
import com.st.lms.model.Author;
import com.st.lms.model.Book;
import com.st.lms.model.Publisher;

public class LmsService {
	
	
	/**
	 * Adds the book to the database
	 * @param book
	 * @param author
	 * @param publisher
	 * @throws Will throw IllegalStateExceptions if:
	 * 		Book already exists or the book's author/publisher is not in database.
	 * 		Hint: call createAuthor and createPublisher	 
	 */
	public static void createBook(Book book, Author author, Publisher publisher){
		BookDaoImpl bookDao = new BookDaoImpl();
		
		if((bookDao.find(book)) == null){
			
			AuthorDaoImpl authDao = new AuthorDaoImpl();
			if(authDao.find(author.getId()) == null){
				//TODO: Throw Exception
				throw new IllegalStateException("Required Author not Found");
			}
			
			PublisherDaoImpl pubDao = new PublisherDaoImpl();
			if(pubDao.find(publisher.getId()) == null){
				//TODO: Throw Exception
				throw new IllegalStateException("Required Publisher not Found");
			}
			
			bookDao.save(book);
			
		}else{
			//TODO: Throw Exception
			throw new IllegalStateException("Book already exists.");
		}
	}
	
	
	public static Author createAuthor(Author author){
		AuthorDaoImpl dao = new AuthorDaoImpl();
		return dao.save(author);
	}
	
	public static Publisher createPublisher(Publisher publisher){
		PublisherDaoImpl dao = new PublisherDaoImpl();
		return dao.save(publisher);
	}
	
	//Find
	public static Author findAuthor(String name){
		AuthorDaoImpl dao = new AuthorDaoImpl();
		Author author = dao.find(name);
		
		return author;
	}
	
	public static Book findBook(String title){
		BookDaoImpl dao = new BookDaoImpl();
		Book book = dao.find(title);
		
		return book;
	}
	
	
	public static Publisher findPublisher(String name){
		PublisherDaoImpl dao = new PublisherDaoImpl();
		Publisher publisher = dao.find(name);
		
		return publisher;
	}
	
	public static Author findAuthor(int id){
		AuthorDaoImpl dao = new AuthorDaoImpl();
		Author author = dao.find(id);
		return author;
	}
	
	public static Book findBook(int id){
		BookDaoImpl dao = new BookDaoImpl();
		Book book = dao.find(id);
		
		return book;
	}
	
	
	public static Publisher findPublisher(int id){
		PublisherDaoImpl dao = new PublisherDaoImpl();
		Publisher publisher = dao.find(id);
		
		return publisher;
	}
	
	public static List<Author> allAuthors(){
		AuthorDaoImpl dao = new AuthorDaoImpl();
		List<Author> list = dao.findAll();
		return list;
	}
	
	
	public static List<Book> allBooks(){
		BookDaoImpl dao = new BookDaoImpl();
		List<Book> list = dao.findAll();
		return list;
	}
	
	public static List<Publisher> allPublisher(){
		PublisherDaoImpl dao = new PublisherDaoImpl();
		List<Publisher> list = dao.findAll();
		return list;
	}
	
	public static void updateBook(Book book){
		BookDaoImpl dao = new BookDaoImpl();
		dao.update(book);
	}
	
	public static void updateAuthor(Author author){
		AuthorDaoImpl dao = new AuthorDaoImpl();
		dao.update(author);
	}
	public static void updatePublisher(Publisher publisher){
		PublisherDaoImpl dao = new PublisherDaoImpl();
		dao.update(publisher);
	}
	
	public void destroyAuthor(Author author){
		AuthorDaoImpl dao = new AuthorDaoImpl();
		BookDaoImpl bookDao = new BookDaoImpl();
		bookDao.findAll()
			.stream()
			.filter(e -> e.getAuthor_id()==author.getId())
			.forEach(e -> destroyBook(e));
		dao.delete(author);
	}
	
	public void destroyBook(Book book){
		BookDaoImpl dao = new BookDaoImpl();
		List<Book> bookList = dao.findAll();
		
		if(!bookList
				.stream()
				.anyMatch(b -> (b.getAuthor_id()==book.getAuthor_id())
						&& b.getId() != book.getId())
		){
			AuthorDaoImpl aDao = new AuthorDaoImpl();
			aDao.delete(aDao.find(book.getAuthor_id()));
		}
		
		if(!bookList
			.stream()
			.anyMatch(b -> (b.getPublisher_id()==book.getPublisher_id())
					&& b.getId() != book.getId())
		){
			PublisherDaoImpl pDao = new PublisherDaoImpl();
			pDao.delete(pDao.find(book.getPublisher_id()));
		}
		
		dao = new BookDaoImpl();
		dao.delete(book);
	}
	
	public void destroyPublisher(Publisher publisher){
		PublisherDaoImpl dao = new PublisherDaoImpl();
		BookDaoImpl bookDao = new BookDaoImpl();
		bookDao.findAll()
			.stream()
			.filter(e -> e.getPublisher_id()==publisher.getId())
			.forEach(b -> destroyBook(b));
		
		dao.delete(publisher);
	}

}
