package com.stardapio.webservice.ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jettison.json.JSONArray;

import com.stardapio.webservice.bean.Item;
import com.stardapio.webservice.bean.Pedido;
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
		List<Pedido> pedidosSession;
		List<Pedido> pedidosBanco = new PedidoModel().getPedidos(idRestaurant);
		
		if (session.getAttribute("pedidos").equals("vazio")) {
			pedidosSession = new ArrayList<Pedido>();
		} else {
			pedidosSession = (List<Pedido>) session.getAttribute("pedidos");
		}
		List<Pedido> pedidosNovos = new ArrayList<Pedido>();

		for (Pedido p : pedidosSession) {
			if (!pedidosSession.contains(p)) {
				p.setVisualizado(true);
				pedidosSession.add(p);
				pedidosNovos.add(p);
			}
		}

		List<String> pedidosString = new ArrayList<String>();

		for (Pedido p : pedidosBanco) {
			for (Item i : p.getItens()) {
				pedidosString.add(i.getName() + " " + p.getMesa());
			}
		}

		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("application/json");

		resp.getWriter().write(new JSONArray(pedidosString).toString());
		resp.getWriter().flush();
	}
}
