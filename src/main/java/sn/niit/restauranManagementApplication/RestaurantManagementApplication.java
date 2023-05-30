package sn.niit.restauranManagementApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sn.niit.restauranManagementApplication.domain.Category;
import sn.niit.restauranManagementApplication.domain.Product;
import sn.niit.restauranManagementApplication.domain.Role;
import sn.niit.restauranManagementApplication.repository.RoleRepository;
import sn.niit.restauranManagementApplication.serviceImpl.CategoryServiceImpl;
import sn.niit.restauranManagementApplication.serviceImpl.ProductServiceImpl;

@SpringBootApplication
public class RestaurantManagementApplication implements CommandLineRunner {

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	@Autowired
	private ProductServiceImpl productServiceImpl;
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(RestaurantManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		roleRepository.save(new Role("ROLE_USER"));
		roleRepository.save(new Role("ROLE_EMPLOYEE"));
		// Categories
		categoryServiceImpl
				.saveOrUpdateCategory(new Category("Desserts", "Description for Desserts", "menu-4-370x278.jpg",
						"linearicons-ice-cream"));
		categoryServiceImpl
				.saveOrUpdateCategory(
						new Category("Pizzas", "Description for Pizzas", "menu-2-370x278.jpeg", "linearicons-pizza"));
		categoryServiceImpl
				.saveOrUpdateCategory(
						new Category("Salades", "Description for Salades", "menu-1-370x278.png", "linearicons-leaf"));
		categoryServiceImpl
				.saveOrUpdateCategory(new Category("Hamburgers", "Description for Burgers", "menu-3-370x278.jpg",
						"linearicons-hamburger"));
		categoryServiceImpl
				.saveOrUpdateCategory(new Category("Boissons", "Description for Boissons", "menu-5-370x278.jpg",
						"linearicons-coffee-cup"));
		categoryServiceImpl
				.saveOrUpdateCategory(
						new Category("Plats", "Description for Plats", "menu-6-370x278.png", "linearicons-steak"));

		Category saladCategory = categoryServiceImpl.findByCategoryName("Salades");

		// Salad fake products
		productServiceImpl.saveOrUpdateProduct(new Product("Salade 1", "Description for Salad 1", 0.0,
				"blank_product.png", saladCategory));
		productServiceImpl.saveOrUpdateProduct(new Product("Salade 2", "Descriptionfor Salad 2", 0.0,
				"blank_product.png",
				categoryServiceImpl.findByCategoryName("Salades")));
		productServiceImpl.saveOrUpdateProduct(new Product("Salade 3", "Description for Salad 3", 0.0,
				"blank_product.png",
				categoryServiceImpl.findByCategoryName("Salades")));

		// Pizzas fake products
		// productServiceImpl.saveOrUpdateProduct(new Product("Pizza 1", "Description
		// for Pizza 1", 0.0,
		// "blank_product.png",
		// categoryServiceImpl.findByCategoryName("Pizzas")));
		// productServiceImpl.saveOrUpdateProduct(new Product("Pizza 2", "Description
		// for Pizza 2", 0.0,
		// "blank_product.png",
		// categoryServiceImpl.findByCategoryName("Pizzas")));
		// productServiceImpl.saveOrUpdateProduct(new Product("Pizza 3", "Description
		// for Pizza 3", 0.0,
		// "blank_product.png",
		// categoryServiceImpl.findByCategoryName("Pizzas")));
	}

}
