package it.cast.jiewen.cart.servlet;

import it.cast.jiewen.book.domain.Book;
import it.cast.jiewen.book.service.BookService;
import it.cast.jiewen.cart.domain.Cart;
import it.cast.jiewen.cart.domain.Cartitem;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;

public class CartServlet extends BaseServlet {
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.print("b");
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		String bid = request.getParameter("bid");
		Book book = new BookService().load(bid);
		Cartitem cartitem = new Cartitem();
		int count = Integer.parseInt(request.getParameter("count"));
		cartitem.setBook(book);
		cartitem.setCount(count);
		cart.add(cartitem);

		return "f:/jsps/cart/list.jsp";
	}

	public String clear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.clearAll();
		return "f:/jsps/cart/list.jsp";
	}

	public String clearByBid(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		String bid = request.getParameter("bid");
		cart.clearBybid(bid);
		return "f:/jsps/cart/list.jsp";
	}
}
