package it.cast.jiewen.book.servlet;

import it.cast.jiewen.book.service.BookService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;

public class BookServlet extends BaseServlet {
	private BookService bookService = new BookService();

	public String load(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1. �õ�����bid 2. ��ѯ�õ�Book 3. ���棬ת����desc.jsp
		 */
		request.setAttribute("book",
				bookService.load(request.getParameter("bid")));
		return "f:/jsps/book/desc.jsp";
	}

	public String findAll(HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("booklist", bookService.findAll());
		return "f:/jsps/book/list.jsp";
	}

	public String findByCid(HttpServletRequest request,
			HttpServletResponse response) {
		request.setAttribute("booklist",
				bookService.findByCid(request.getParameter("cid")));
		return "f:/jsps/book/list.jsp";
	}
}
