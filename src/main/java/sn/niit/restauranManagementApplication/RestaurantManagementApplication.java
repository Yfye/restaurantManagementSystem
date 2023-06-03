package sn.niit.restauranManagementApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sn.niit.restauranManagementApplication.config.Seeder;
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
		Seeder seeder = new Seeder(categoryServiceImpl, productServiceImpl, roleRepository);
		seeder.seed();
	}

}
