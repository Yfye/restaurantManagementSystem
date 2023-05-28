package sn.niit.restauranManagementApplication.service;

import java.util.List;

import org.springframework.data.domain.Page;

import sn.niit.restauranManagementApplication.domain.Category;
import sn.niit.restauranManagementApplication.domain.Product;

public interface ProductService {
	void saveOrUpdateProduct(Product product);

	Product findById(Long id);

	List<Product> getProducts();

	List<Product> findProductsByKeyword(String keyword);

	void deleteProduit(Long productId);

	Page<Product> findPaginated(int pageNumber, int pageSize);

	List<Product> findByCategory(Category category);

}
