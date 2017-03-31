package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Category;
import persistence.CategoryDao;
import persistence.JDBCDriver;

public class CategoryDaoImpl implements CategoryDao {

	private static String SQL_FIND_ALL_CATEGORIES = "SELECT * FROM PUBLIC.CATEGORY";
	private Connection con = JDBCDriver.getConnection();

	@Override
	public List<Category> findAllCategories() {
		List<Category> categories = new ArrayList<Category>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(SQL_FIND_ALL_CATEGORIES);

			rs = pst.executeQuery();
			while (rs.next()) {

				Integer id = rs.getInt("id");
				String name = rs.getString("name");

				Category category = new Category().setId(id).setName(name);
				categories.add(category);
			}

			return categories;

		} catch (SQLException e) {
			System.err.println(e);
			return null;
		} finally {
			try {
				rs.close();
				pst.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

}
