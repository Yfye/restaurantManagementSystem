package sn.niit.restauranManagementApplication.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sn.niit.restauranManagementApplication.domain.Category;
import sn.niit.restauranManagementApplication.repository.CategoryRepository;
import sn.niit.restauranManagementApplication.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService

{
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void saveOrUpdateCategory(Category category) {
		categoryRepository.save(category);

	}

	@Override
	public Category findByCategoryName(String categoryName) {
		Category category = categoryRepository.findByName(categoryName);

		if (category.equals(null))
			throw new RuntimeException("Category does not exist !");
		return category;
	}

	@Override
	public List<Category> getCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category findById(Long id) {
		Category category = null;
		Optional<Category> optionalCategorie = categoryRepository.findById(id);
		if (!optionalCategorie.isEmpty()) {
			category = optionalCategorie.get();

		} else {
			throw new RuntimeException("Aucune category id = " + id + "n'a ete trouve");
		}

		return category;
	}

	@Override
	public void deleteCategorie(Long Id) {
		categoryRepository.deleteById(Id);

	}

	@Override
	public Page<Category> findPaginated(int pageNumber, int pageSize) {
		PageRequest pageable = PageRequest.of(pageNumber - 1, pageSize);
		return categoryRepository.findAll(pageable);
	}

}
