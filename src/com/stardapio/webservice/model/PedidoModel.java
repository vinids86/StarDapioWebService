package com.stardapio.webservice.model;

import java.util.List;

import com.stardapio.webservice.bean.Pedido;
import com.stardapio.webservice.dao.DAO;

public class PedidoModel {

	public List<Pedido> getPedidos(long idRestaurant) {
		return new DAO().getPedidos(idRestaurant);
	}

}
