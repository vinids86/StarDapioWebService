package com.stardapio.webservice.model;

import java.util.Set;

import com.stardapio.webservice.bean.Pedido;
import com.stardapio.webservice.dao.DAO;

public class PedidoModel {

	public Set<Pedido> getPedidos(long idRestaurant) {
		DAO dao = new DAO();
		Set<Pedido> pedidos = dao.getPedidos(idRestaurant);
		
		/*
		List<String> stringPedidos = new ArrayList<String>();
		
		for (Pedido p : pedidos) {
			for (Item i : p.getItens()) {
				stringPedidos.add(i.getName() + " " + p.getMesa());
			}
		}
		*/
		
		// dao.setVisualizado(true, idRestaurant);
		
		return pedidos;
	}
}
