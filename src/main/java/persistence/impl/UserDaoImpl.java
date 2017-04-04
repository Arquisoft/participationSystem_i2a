package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.User;
import persistence.Database;
import persistence.UserDao;

public class UserDaoImpl implements UserDao {
	// private static String SQL_FIND_USER_BY_ID =
	// Conf.getInstance().getProperty("SQL_FIND_USER_BY_ID");

	private static String SQL_FIND_USER_BY_ID = "SELECT * FROM PUBLIC.USER WHERE ID=?";
	private static String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM PUBLIC.USER WHERE EMAIL=?";
	private static String SQL_FIND_ALL_EMAILS = "SELECT EMAIL FROM PUBLIC.USER";
	private static String SQL_INSERT_USER = "INSERT INTO PUBLIC.USER (dni, nombre, apellidos, "
			+ "password, email, nacimiento, direccion, nacionalidad, polling) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private Connection con = Database.getConnection();

	@Override
	public User getUserById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(SQL_FIND_USER_BY_ID);
			pst.setInt(1, id);

			rs = pst.executeQuery();
			rs.next();

			Integer idBase = rs.getInt("id");
			String dni = rs.getString("dni");
			String name = rs.getString("nombre");
			String surname = rs.getString("apellidos");
			String email = rs.getString("email");
			Date birth = rs.getDate("nacimiento");
			String nationality = rs.getString("nacionalidad");
			String address = rs.getString("direccion");
			int polling = rs.getInt("polling");
			String pass = rs.getString("password");

			User user = new User(dni, name, surname, birth, address, email, nationality, polling);
			user.setId(idBase);
			user.setPassword(pass);

			return user;

		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	@Override
	public List<String> findAllEmails() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String> emails = new ArrayList<String>();
		try {
			pst = con.prepareStatement(SQL_FIND_ALL_EMAILS);

			rs = pst.executeQuery();
			while (rs.next()) {
				emails.add(rs.getString(1));
			}

			return emails;

		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	@Override
	public void createUser(User user) {
		// TODO de momento la password sera siempre "pass", 
		// luego se cambiara a la generada en la primera entrega
		
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(SQL_INSERT_USER);
			pst.setString(1, user.getDni());
			pst.setString(2, user.getFirstName());
			pst.setString(3, user.getLastName());
			pst.setString(4, "pass");
			pst.setString(5, user.getEmail());
			pst.setDate(6, new java.sql.Date(user.getBirthdate().getTime()));
			pst.setString(7, user.getAddress());
			pst.setString(8, user.getNationality());
			pst.setInt(9, user.getPollingStation());

			pst.executeUpdate();

		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}

	}

	@Override
	public User getUserByEmail(String email) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(SQL_FIND_USER_BY_EMAIL);
			pst.setString(1, email);

			rs = pst.executeQuery();
			rs.next();

			Integer idBase = rs.getInt("id");
			String dni = rs.getString("dni");
			String name = rs.getString("nombre");
			String surname = rs.getString("apellidos");
			String emailEste = rs.getString("email");
			Date birth = rs.getDate("nacimiento");
			String nationality = rs.getString("nacionalidad");
			String address = rs.getString("direccion");
			int polling = rs.getInt("polling");
			String pass = rs.getString("password");

			User user = new User(dni, name, surname, birth, address, emailEste, nationality, polling);
			user.setId(idBase);
			user.setPassword(pass);

			return user;

		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}
}
