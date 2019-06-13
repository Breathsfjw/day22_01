package it.cast.jiewen.cart.domain;

import it.cast.jiewen.book.domain.Book;

import java.math.BigDecimal;

public class Cartitem {
	private Book book;
	private int count;

	public double getSubtotal() {
		BigDecimal d1 = new BigDecimal(book.getPrice() + "");
		BigDecimal d2 = new BigDecimal(count + "");
		return d1.multiply(d2).doubleValue();
	}

	@Override
	public String toString() {
		return "Cartitem [book=" + book + ", count=" + count + "]";
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
