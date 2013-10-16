package com.stardapio.webservice.ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stardapio.webservice.bean.Item;
import com.stardapio.webservice.bean.Pedido;
import com.stardapio.webservice.model.PedidoModel;

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

		Set<Pedido> pedidosBanco = new PedidoModel().getPedidos(idRestaurant);		
		
		session.setAttribute("pedidos", pedidosBanco);
		req.setAttribute("pedidosInit", pedidosBanco);
		
		String path = "pedidos.jsp";
		RequestDispatcher view = req.getRequestDispatcher(path);
		view.forward(req, resp);
	}
}
