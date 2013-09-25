package com.stardapio.webservice.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jettison.json.JSONArray;

import com.stardapio.webservice.model.PedidoModel;

public class PedidoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -122113649281403690L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		// long idRestaurant = (Long) session.getAttribute("idRestaurant");
		long idRestaurant = 1;

		List<String> pedidos = new PedidoModel().getPedidos(idRestaurant);

		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("application/json");

		resp.getWriter().write(new JSONArray(pedidos).toString());
		resp.getWriter().flush();
	}
}
