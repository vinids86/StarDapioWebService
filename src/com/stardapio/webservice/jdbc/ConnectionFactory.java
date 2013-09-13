package com.stardapio.webservice.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver()); //Essa linha foi a diferença
			return DriverManager.getConnection(
					"jdbc:mysql://localhost/stardapio?autoReconnect=true&useUnicode=true&characterEncoding=utf8",
					"root", "stardapio2013");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
