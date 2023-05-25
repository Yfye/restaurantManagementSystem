package sn.niit.restauranManagementApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sn.niit.restauranManagementApplication.domain.Category;
import sn.niit.restauranManagementApplication.serviceImpl.CategoryServiceImpl;
import sn.niit.restauranManagementApplication.serviceImpl.ProductServiceImpl;

@Controller
@RequestMapping("/site")
public class SiteController {

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;

	@Autowired
	private ProductServiceImpl productServiceImpl;

	@GetMapping("/home")
	public ModelAndView showHome() {
		ModelAndView homeModel = new ModelAndView("site/home");
		homeModel.addObject("categoryService", categoryServiceImpl);
		return homeModel;
	}

	@GetMapping("/menu")
	public ModelAndView showMenu() {
		ModelAndView menuModel = new ModelAndView("site/menu");
		return menuModel;
	}

	@GetMapping("/salades")
	public ModelAndView showSalades() {
		ModelAndView saladModel = new ModelAndView("site/salades");
		Category saladCategory = categoryServiceImpl.findByCategoryName("Salades");
		saladModel.addObject("saladCategory", saladCategory);
		saladModel.addObject("productService", productServiceImpl);
		return saladModel;
	}

	@GetMapping("/pizzas")
	public ModelAndView showPizzas() {
		ModelAndView pizzaModel = new ModelAndView("site/pizzas");
		return pizzaModel;
	}

	@GetMapping("/hamburgers")
	public ModelAndView showBurgers() {
		ModelAndView burgerModel = new ModelAndView("site/burgers");
		return burgerModel;
	}

	@GetMapping("/desserts")
	public ModelAndView showDessert() {
		ModelAndView dessertModel = new ModelAndView("site/desserts");
		return dessertModel;
	}

	@GetMapping("/boissons")
	public ModelAndView showBoissons() {
		ModelAndView drinkModel = new ModelAndView("site/boissons");
		return drinkModel;
	}

	@GetMapping("/plats")
	public ModelAndView showPlats() {
		ModelAndView dishModel = new ModelAndView("site/plats");
		return dishModel;
	}

	@GetMapping("/about-us")
	public String showAbout() {
		return "site/about-us";
	}

	@GetMapping("/contact")
	public String showContact() {
		return "site/contact";
	}

}
