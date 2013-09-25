package com.stardapio.webservice.model;

import java.util.ArrayList;
import java.util.List;

import com.stardapio.webservice.bean.Item;
import com.stardapio.webservice.bean.Pedido;
import com.stardapio.webservice.dao.DAO;

public class PedidoModel {

	public List<String> getPedidos(long idRestaurant) {
		DAO dao = new DAO();
		List<Pedido> pedidos = dao.getPedidos(idRestaurant);
		List<String> stringPedidos = new ArrayList<String>();
		
		for (Pedido p : pedidos) {
			for (Item i : p.getItens()) {
				stringPedidos.add(i.getName() + " " + p.getMesa());
			}
		}
		
		dao.setVisualizado(true, idRestaurant);
		
		return stringPedidos;
	}
}
