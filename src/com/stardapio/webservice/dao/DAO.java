package com.stardapio.webservice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.stardapio.webservice.bean.ContainerTypeAndSubType;
import com.stardapio.webservice.bean.Item;
import com.stardapio.webservice.bean.Pedido;
import com.stardapio.webservice.bean.Restaurant;
import com.stardapio.webservice.bean.SubType;
import com.stardapio.webservice.bean.Type;
import com.stardapio.webservice.jdbc.ConnectionFactory;

public class DAO {
	private Connection connection;

	public DAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public List<Type> getType(int idRestaurante) {
		String sql = "select a.id_restaurant, b.`name` as 'name', a.id_type, c.`type`, a.urlImage "
				+ "from item as a  left join restaurant as b "
				+ "on a.id_restaurant = b.id_restaurant left join `type` as c "
				+ "on a.id_type = c.id_type  where a.id_restaurant = "
				+ idRestaurante
				+ " group by a.id_restaurant, b.`name`, a.id_type, c.`type`;";
		try {
			List<Type> types = new ArrayList<Type>();
			PreparedStatement stmt = this.connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Type type = new Type();
				type.setId_restaurant(rs.getInt("id_restaurant"));
				type.setName(rs.getString("name"));
				type.setId_type(rs.getInt("id_type"));
				type.setType(rs.getString("type"));
				type.setUrlImage(rs.getString("urlImage"));

				types.add(type);
			}
			rs.close();
			stmt.close();
			return types;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public ContainerTypeAndSubType getItemTypeAndSubType(int idRestaurante) {
		String sql = "SELECT a.id_restaurant, b.`name` as 'name', a.id_type, c.`type`, d.id_sub_type, d.`type` "
				+ "FROM item as a LEFT JOIN restaurant as b on a.id_restaurant = b.id_restaurant "
				+ "LEFT JOIN `type` as c on a.id_type = c.id_type "
				+ "LEFT JOIN `sub_type` as d on a.id_type = d.id_type "
				+ "WHERE a.id_restaurant = "
				+ idRestaurante
				+ " AND id_sub_type != 'NULL' "
				+ " GROUP BY a.id_restaurant, b.`name`, a.id_type, c.`type`, d.id_sub_type, d.`type`";
		try {
			ContainerTypeAndSubType container = new ContainerTypeAndSubType();
			container.setTypes(getType(idRestaurante));
			List<SubType> subTypes = new ArrayList<SubType>();
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				SubType subType = new SubType();
				subType.setId_restaurant(rs.getInt("id_restaurant"));
				subType.setName(rs.getString("name"));
				subType.setId_type(rs.getInt("id_type"));
				subType.setType(rs.getString("type"));
				subType.setidSubType(rs.getInt("id_sub_type"));
				subTypes.add(subType);
			}
			container.setSubTypes(subTypes);

			rs.close();
			stmt.close();

			return container;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Item> getItemType(int idRestaurante, int idType) {
		String sql = "select * from item " + "where id_restaurant = "
				+ idRestaurante + " and id_type = " + idType;
		try {
			List<Item> itens = new ArrayList<Item>();
			PreparedStatement stmt = this.connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Item item = new Item();
				item.setIdItem(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getDouble("price"));
				item.setDescription(rs.getString("description"));
				item.setUrlImage(rs.getString("urlImage"));
				item.setIdRestaurante(rs.getInt("id_restaurant"));
				item.setIdType(rs.getInt("id_type"));

				itens.add(item);
			}
			rs.close();
			stmt.close();
			return itens;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Item> getMenu(int idRestaurante) {
		String sql = "select * from item " + "where id_restaurant = "
				+ idRestaurante;
		try {
			List<Item> itens = new ArrayList<Item>();
			PreparedStatement stmt = this.connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Item item = new Item();
				item.setIdItem(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getDouble("price"));
				item.setDescription(rs.getString("description"));
				item.setUrlImage(rs.getString("urlImage"));
				item.setIdRestaurante(rs.getInt("id_restaurant"));
				item.setIdType(rs.getInt("id_type"));

				itens.add(item);
			}
			rs.close();
			stmt.close();
			return itens;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Restaurant> getListaRestaurantes() {
		String sql = "select * from restaurant";
		try {
			List<Restaurant> restaurantes = new ArrayList<Restaurant>();
			PreparedStatement stmt = this.connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Restaurant restaurante = new Restaurant();
				restaurante.setIdRestaurant(rs.getInt("id_restaurant"));
				restaurante.setName(rs.getString("name"));
				restaurante.setAddress(rs.getString("address"));
				restaurante.setLat(rs.getDouble("lat"));
				restaurante.setLng(rs.getDouble("lng"));

				restaurantes.add(restaurante);
			}
			rs.close();
			stmt.close();
			return restaurantes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void inserirItem(Item item) {
		String sql = "insert int item"
				+ "(name, price, description, urlImage, id_restaurant, id_type)"
				+ "values (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, item.getName());
			stmt.setDouble(2, item.getPrice());
			stmt.setString(3, item.getDescription());
			stmt.setString(4, item.getUrlImage());
			stmt.setInt(5, item.getIdRestaurante());
			stmt.setInt(6, item.getIdType());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Restaurant buscarRestaurante(int id) {
		String sql = "select * from restaurant where id_restaurant = " + id;

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			Restaurant restaurante = new Restaurant();

			while (rs.next()) {
				restaurante.setIdRestaurant(rs.getInt("id_restaurant"));
				restaurante.setName(rs.getString("name"));
				restaurante.setAddress(rs.getString("address"));
				restaurante.setLat(rs.getDouble("lat"));
				restaurante.setLng(rs.getDouble("lng"));
			}
			rs.close();
			stmt.close();

			return restaurante;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void inserirRestaurante(Restaurant restaurant) {
		String sql = "insert into restaurant " + "(name, address, lat, lng)"
				+ "values (?, ?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, restaurant.getName());
			stmt.setString(2, restaurant.getAddress());
			stmt.setDouble(3, restaurant.getLat());
			stmt.setDouble(4, restaurant.getLng());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void adicionaPedido(Pedido pedido) {
		String sql = "insert into pedido "
				+ "(id_cliente, id_restaurant, item, mesa, visualizado)"
				+ "values (?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			List<Item> itens = pedido.getItens();
			for (Item i : itens) {
				stmt.setLong(1, pedido.getIdCliente());
				stmt.setLong(2, pedido.getIdRestaurant());
				stmt.setInt(3, i.getIdItem());
				stmt.setLong(4, pedido.getMesa());
				stmt.setInt(5, 0);
			}

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Pedido> getPedido(long idRestaurant, long mesa) {
		String sql = "select * from pedido " + "where id_restaurant = "
				+ idRestaurant + " and mesa = " + mesa;
		try {
			List<Pedido> pedidos = new ArrayList<Pedido>();
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Pedido pedido = new Pedido();
				pedido.setIdCliente(rs.getLong("id_cliente"));
				pedido.setIdRestaurant(rs.getLong("id_restaurant"));
				pedido.addItem(getItem(rs.getLong("item")));
				pedido.setMesa(rs.getLong("mesa"));
				pedidos.add(pedido);
			}
			rs.close();
			stmt.close();
			return pedidos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Item getItem(long id) {
		String sql = "select * from item " + "where id = " + id;
		try {
			Item item = new Item();
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				item.setIdItem(rs.getInt("id"));
				item.setName(rs.getString("name"));
				item.setPrice(rs.getDouble("price"));
				item.setDescription(rs.getString("description"));
				item.setUrlImage(rs.getString("urlImage"));
				item.setIdRestaurante(rs.getInt("id_restaurant"));
				item.setIdType(rs.getInt("id_type"));
			}
			rs.close();
			stmt.close();
			return item;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Pedido> getPedido(long idRestaurant, long mesa, long idCliente) {
		String sql = "select * from pedido " + "where id_restaurant = "
				+ idRestaurant + " and mesa = " + mesa + " and id_cliente = "
				+ idCliente;
		try {
			List<Pedido> pedidos = new ArrayList<Pedido>();
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Pedido pedido = new Pedido();
				pedido.setIdCliente(rs.getLong("id_cliente"));
				pedido.setIdRestaurant(rs.getLong("id_restaurant"));
				pedido.addItem(getItem(rs.getLong("item")));
				pedido.setMesa(rs.getLong("mesa"));
				pedidos.add(pedido);
			}
			rs.close();
			stmt.close();
			return pedidos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Pedido> getPedidos(long idRestaurant) {
		String sql = "select * from pedido " + "where id_restaurant = "
				+ idRestaurant + " and visualizado = false";
		try {
			List<Pedido> pedidos = new ArrayList<Pedido>();
			PreparedStatement stmt = this.connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Pedido pedido = new Pedido();
				pedido.setIdCliente(rs.getLong("id_cliente"));
				pedido.setIdRestaurant(rs.getLong("id_restaurant"));
				pedido.addItem(getItem(rs.getLong("item")));
				pedido.setMesa(rs.getLong("mesa"));
				pedidos.add(pedido);
			}
			rs.close();
			stmt.close();
			return pedidos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void setVisualizado(boolean b, long idRestaurant) {
		String sql = "update pedido set visualizado = true where id_restaurant = " + idRestaurant;
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * public void deleteRestaurante(int id) {
	 * 
	 * String sql = "delete from restaurant where id_restaurant = " + id; try {
	 * PreparedStatement stmt = connection.prepareStatement(sql);
	 * stmt.execute(); stmt.close(); } catch (SQLException e) { throw new
	 * RuntimeException(e); } }
	 */
}
