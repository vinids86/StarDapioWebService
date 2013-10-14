package com.stardapio.webservice.ajax;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainPedidosServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8516899114574343100L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		// long idRestaurant = (Long) session.getAttribute("idRestaurant");
		long idRestaurant = 1;
		session.setAttribute("pedidos", "vazio");
		
		String path = "pedidos.jsp";
		RequestDispatcher view = req.getRequestDispatcher(path);
		view.forward(req, resp);
	}
}
