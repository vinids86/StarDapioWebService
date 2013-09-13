package com.stardapio.webservice.bean;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Carrinho {
	private List<Item> pedidos;
	private Item pedido;

	public Carrinho() {
		pedidos = new ArrayList<Item>();
	}

	public List<Item> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Item> pedidos) {
		this.pedidos = pedidos;
	}

	public Item getPedido() {
		return pedido;
	}

	public void setPedido(Item pedido) {
		this.pedido = pedido;
	}

	public void addPedido(Item pedido) {
		pedidos.add(pedido);
	}
}