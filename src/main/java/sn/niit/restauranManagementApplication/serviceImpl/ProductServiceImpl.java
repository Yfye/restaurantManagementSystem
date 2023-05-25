package sn.niit.restauranManagementApplication.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sn.niit.restauranManagementApplication.domain.Category;
import sn.niit.restauranManagementApplication.domain.Product;
import sn.niit.restauranManagementApplication.repository.ProductRepository;
import sn.niit.restauranManagementApplication.service.CategoryService;
import sn.niit.restauranManagementApplication.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryService categoryService;

	@Override
	public void saveOrUpdateProduct(Product product) {
		Category category = categoryService.findById(product.getCategory().getCategoryId());

		if (category == null)
			throw new RuntimeException("Category does not exist!");
		productRepository.save(product);

	}

	@Override
	public List<Product> findByCategory(Category category) {
		return productRepository.findByCategory(category);
	}

	@Override
	public Product findById(Long productId) {
		Product produit = null;
		Optional<Product> optionalProduit = productRepository.findById(productId);
		if (!optionalProduit.isEmpty()) {
			produit = optionalProduit.get();
		} else {
			throw new RuntimeException("Aucun produit trouve");
		}
		return produit;
	}

	@Override
	public List<Product> getProducts() {

		return productRepository.findAll();
	}

	@Override
	public List<Product> findProductsByKeyword(String keyword) {
		return productRepository.findByKeyword(keyword);
	}

	@Override
	public void deleteProduit(Long id) {
		productRepository.deleteById(id);

	}

	@Override
	public Page<Product> findPaginated(int pageNumber, int pageSize) {
		PageRequest pageable = PageRequest.of(pageNumber - 1, pageSize);

		return productRepository.findAll(pageable);
	}

}
