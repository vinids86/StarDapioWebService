package com.stardapio.webservice.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import com.stardapio.webservice.bean.Pedido;
import com.stardapio.webservice.dao.DAO;

@Path("/pedido")
public class PedidoController {

	@POST
	@Path("/adiciona")
	@Produces("application/json")
	@Consumes("application/json")
	public String adiciona(Pedido pedido) {
		new DAO().adicionaPedido(pedido);
		return "ok";
	}

	@GET
	@Path("/restaurante/{id_restaurant}/mesa/{mesa}")
	@Produces("application/json")
	public String getPedido(@PathParam("id_restaurant") long idRestaurante,
			@PathParam("mesa") long mesa) {
		return new Gson().toJson(new DAO().getPedido(idRestaurante, mesa));

	}
	
	@GET
	@Path("/restaurante/{id_restaurant}/mesa/{mesa}/cliente/{id_cliente}")
	@Produces("application/json")
	public String getPedido(@PathParam("id_restaurant") long idRestaurante,
			@PathParam("mesa") long mesa, @PathParam("id_cliente") long idCliente) {
		return new Gson().toJson(new DAO().getPedido(idRestaurante, mesa, idCliente));

	}
}
