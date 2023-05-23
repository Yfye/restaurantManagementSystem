package sn.niit.restauranManagementApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sn.niit.restauranManagementApplication.domain.Categorie;
import sn.niit.restauranManagementApplication.serviceImpl.CategorieServiceImpl;

@SpringBootApplication
public class RestaurantManagementApplication implements CommandLineRunner {

	@Autowired
	private CategorieServiceImpl categorieServiceImpl;

	public static void main(String[] args) {
		SpringApplication.run(RestaurantManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		categorieServiceImpl
				.saveOrUpdateCategorie(new Categorie("Desserts", "Description for Desserts", "menu-4-370x278.jpg",
						"linearicons-ice-cream"));
		categorieServiceImpl
				.saveOrUpdateCategorie(
						new Categorie("Pizzas", "Description for Pizzas", "menu-2-370x278.jpeg", "linearicons-pizza"));
		categorieServiceImpl
				.saveOrUpdateCategorie(
						new Categorie("Salades", "Description for Salades", "menu-1-370x278.png", "linearicons-leaf"));
		categorieServiceImpl
				.saveOrUpdateCategorie(new Categorie("Burgers", "Description for Burgers", "menu-3-370x278.jpg",
						"linearicons-hamburger"));
		categorieServiceImpl
				.saveOrUpdateCategorie(new Categorie("Boissons", "Description for Boissons", "menu-5-370x278.jpg",
						"linearicons-coffee-cup"));
		categorieServiceImpl
				.saveOrUpdateCategorie(
						new Categorie("Dishes", "Description for Dishes", "menu-6-370x278.png", "linearicons-steak"));
	}

}
