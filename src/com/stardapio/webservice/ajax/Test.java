package com.stardapio.webservice.ajax;

import java.util.ArrayList;
import java.util.List;

import com.stardapio.webservice.bean.Item;
import com.stardapio.webservice.bean.Pedido;

public class Test {
	public static void main(String[] args) {
		List<Item> itens = new ArrayList<Item>();
		Item item = new Item();
		item.setDescription("Descricao");
		item.setIdItem(1);
		item.setIdRestaurante(1);
		item.setIdType(1);
		item.setName("Name");
		item.setPrice(2.5);
		item.setUrlImage("urlImage");
		itens.add(item);
		Pedido pedido1 = new Pedido();
		pedido1.setIdCliente("1");
		pedido1.setIdPedido(1);
		pedido1.setIdRestaurant(1);
		pedido1.setItens(itens);
		pedido1.setMesa(1);
		
		List<Pedido> pedidosBanco = new ArrayList<Pedido>();
		pedidosBanco.add((pedido1));
		List<Pedido> pedidosSession = new ArrayList<Pedido>();
		Pedido pedido2 = new Pedido();
		pedido2.setIdCliente("1");
		pedido2.setIdPedido(2);
		pedido2.setIdRestaurant(1);
		pedido2.setItens(itens);
		pedido2.setMesa(1);
		pedidosSession.add(pedido2);
		List<Pedido> pedidosNovos = new ArrayList<Pedido>();
		
		for(Pedido p : pedidosBanco) {
			if(!pedidosSession.contains(p)) {
				pedidosSession.add(p);
				pedidosNovos.add(p);				
			}
		}
		System.out.println(pedidosNovos);
	}
}
