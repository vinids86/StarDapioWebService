package com.stardapio.webservice.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.google.gson.Gson;
import com.stardapio.webservice.bean.Restaurant;
import com.stardapio.webservice.dao.DAO;
import com.stardapio.webservice.exception.NoContentException;

@Path("/restaurante")
public class RestaurantResource {

	@GET
	@Path("/buscarTodos")
	@Produces("application/json")
	public List<Restaurant> selTodos() {
		return new DAO().getListaRestaurantes();
	}

	@GET
	@Path("/buscarTodosGSON")
	@Produces("application/json")
	public String selTodosGSON() {
		return new Gson().toJson(new DAO().getListaRestaurantes());
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Restaurant getRestaurant(@PathParam("id") int id) {
		Restaurant restaurante = new DAO().buscarRestaurante(id);

		if (restaurante == null)
			throw new NoContentException("Restaurante nao econtrado");

		return restaurante;
	}

	/*
	 * @GET
	 * 
	 * @Path("/delete/{id}")
	 * 
	 * @Produces("application/json") public void
	 * deleteRestaurant(@PathParam("id") int id) { new
	 * DAO().deleteRestaurante(id); }
	 */

	@POST
	@Path("/inserir")
	@Produces("application/json")
	@Consumes("application/json")
	public void inserirRestaurante(Restaurant restaurante) {
		new DAO().inserirRestaurante(restaurante);
	}
}
