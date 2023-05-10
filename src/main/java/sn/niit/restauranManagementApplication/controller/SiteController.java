package sn.niit.restauranManagementApplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller 
@RequestMapping("/site")
public class SiteController 
{
	@GetMapping("/home")
		public String showHome()
	{
		return "site/home";
	}
	@GetMapping("/menu")
	public String showMenu()
	{
		return "site/menu";
	}
	@GetMapping("/salades")
	public String showSalades()
	{
		return "site/salades";
	}
	@GetMapping("/pizzas")
	public String showPizzas()
	{
		return "site/pizzas";
	}
	@GetMapping("/burgers")
	public String showBurgers()
	{
		return "site/burgers";
	}

	@GetMapping("/desserts")
	public String showDessert()
	{
		return "site/desserts";
	}
	@GetMapping("/boissons")
	public String showBoissons()
	{
		return "site/boissons";
	}
	@GetMapping("/plats")
	public String showPlats()
	{
		return "site/plats";
	}
	@GetMapping("/about-us")
	public String showAbout()
	{
		return "site/about-us";
	}
	@GetMapping("/contact")
	public String showContact()
	{
		return "site/contact";
	}



}
