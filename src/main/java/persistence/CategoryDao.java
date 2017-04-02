package persistence;

import java.util.List;

import dto.Category;

public interface CategoryDao {

	public List<Category> findAllCategories();
	public void createCategory(Category cat);
}
