package sn.niit.restauranManagementApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sn.niit.restauranManagementApplication.serviceImpl.CategorieServiceImpl;

@Controller
@RequestMapping("/site")
public class SiteController {

	@Autowired
	private CategorieServiceImpl categorieServiceImpl;

	@GetMapping("/home")
	public ModelAndView showHome() {
		ModelAndView homeModel = new ModelAndView("site/home");
		homeModel.addObject("categorieService", categorieServiceImpl);
		return homeModel;
	}

	@GetMapping("/menu")
	public String showMenu() {
		return "site/menu";
	}

	@GetMapping("/salades")
	public String showSalades() {
		return "site/salades";
	}

	@GetMapping("/pizzas")
	public String showPizzas() {
		return "site/pizzas";
	}

	@GetMapping("/burgers")
	public String showBurgers() {
		return "site/burgers";
	}

	@GetMapping("/desserts")
	public String showDessert() {
		return "site/desserts";
	}

	@GetMapping("/boissons")
	public String showBoissons() {
		return "site/boissons";
	}

	@GetMapping("/plats")
	public String showPlats() {
		return "site/plats";
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
