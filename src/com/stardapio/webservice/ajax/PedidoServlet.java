package com.stardapio.webservice.ajax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
		Set<Pedido> pedidosSession;

		pedidosSession = (Set<Pedido>) session.getAttribute("pedidos");

		Set<Pedido> pedidosBanco = new PedidoModel().getPedidos(idRestaurant);
		Set<Pedido> pedidosNovos = new HashSet<Pedido>();
		
		/*for (Pedido p : pedidosBanco) {
			if ((!pedidosSession.contains(p)) && (!p.isVisualizado())) {
				p.setVisualizado(true);
				pedidosSession.add(p);
				pedidosNovos.add(p);
			}
		}*/
		
		
		
		for(Pedido p : pedidosBanco) {
			if((pedidosSession.add(p)) ) {
				pedidosNovos.add(p);
				p.setVisualizado(true);
			}
		}

		session.setAttribute("pedidos", pedidosSession);
		List<String> pedidosString = new ArrayList<String>();

		for (Pedido p : pedidosNovos) {
			for (Item i : p.getItens()) {
				pedidosString.add(i.getName() + " Mesa: " + p.getMesa());
			}
		}

		resp.setStatus(HttpServletResponse.SC_OK);
		resp.setContentType("application/json");

		resp.getWriter().write(new JSONArray(pedidosString).toString());
		resp.getWriter().flush();
	}
}
