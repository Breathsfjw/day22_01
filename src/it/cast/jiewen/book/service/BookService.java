package it.cast.jiewen.book.service;

import it.cast.jiewen.book.dao.BookDao;
import it.cast.jiewen.book.domain.Book;

import java.util.List;

public class BookService {
	private BookDao bookDao = new BookDao();

	public List<Book> findAll() {
		return bookDao.findAll();
	}

	public List<Book> findByCid(String cid) {
		return bookDao.findByCid(cid);
	}

	public Book load(String bid) {
		return bookDao.findByBid(bid);
	}
}
