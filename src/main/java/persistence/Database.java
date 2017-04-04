package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	public static Connection getConnection() {
		try {
			Connection db = DriverManager.getConnection("jdbc:postgresql:citizens", "postgres", "postgres");
			return db;
		} catch (SQLException e) {
			System.err.println("Error al conectar con la bbdd: ");
			System.err.println(e);
			return null;
		}
	}

}
