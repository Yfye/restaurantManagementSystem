package sn.niit.restauranManagementApplication.service;

import java.util.List;
import org.springframework.data.domain.Page;
import sn.niit.restauranManagementApplication.domain.Category;

public interface CategoryService {
	void saveOrUpdateCategory(Category category);

	Category findById(Long id);

	Category findByCategoryName(String categoryName);

	List<Category> getCategories();

	void deleteCategorie(Long Id);

	Page<Category> findPaginated(int pageNumber, int pageSize);
}
