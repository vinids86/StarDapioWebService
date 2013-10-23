package com.stardapio.webservice.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stardapio.webservice.bean.Item;
import com.stardapio.webservice.dao.DAO;

public class CentralNegociosServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7401823210389862050L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		// long idRestaurant = (Long) session.getAttribute("idRestaurant");
		long idRestaurant = 1;		
		DAO dao = new DAO();
		
		List<Item> menu = dao.getMenu(idRestaurant);
		req.setAttribute("menu", menu);
	}

}
