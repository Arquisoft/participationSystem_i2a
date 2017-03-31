package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import dto.User;
import persistence.JDBCDriver;
import persistence.UserDao;

public class UserDaoImpl implements UserDao {
	// private static String SQL_FIND_USER_BY_ID =
	// Conf.getInstance().getProperty("SQL_FIND_USER_BY_ID");
	private static String SQL_FIND_USER_BY_ID = "SELECT * FROM PUBLIC.USER WHERE ID=?";
	private Connection con = JDBCDriver.getConnection();

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

			User user = new User().setId(idBase).setDni(dni).setName(name).setSurname(surname).setEmail(email)
					.setBirthdate(birth).setNationality(nationality).setAddress(address).setPollingStation(polling)
					.setPassword(pass);

			return user;

		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally {
			try {
				if (rs != null) rs.close();
				if (pst != null) pst.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}
}
