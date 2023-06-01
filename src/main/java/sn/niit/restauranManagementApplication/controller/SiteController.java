package sn.niit.restauranManagementApplication.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sn.niit.restauranManagementApplication.domain.Cart;
import sn.niit.restauranManagementApplication.domain.Category;
import sn.niit.restauranManagementApplication.serviceImpl.CartServiceImpl;
import sn.niit.restauranManagementApplication.serviceImpl.CategoryServiceImpl;
import sn.niit.restauranManagementApplication.serviceImpl.ProductServiceImpl;

@Controller
@RequestMapping("/site")
public class SiteController {

	@Autowired
	private CategoryServiceImpl categoryServiceImpl;

	@Autowired
	private HttpSession httpSession;

	@Autowired
	private ProductServiceImpl productServiceImpl;

	@Autowired
	private CartServiceImpl cartServiceImpl;

	@GetMapping("/home")
	public ModelAndView showHome() {
		ModelAndView homeModel = new ModelAndView("site/home");
		homeModel.addObject("categoryService", categoryServiceImpl);

		Cart sessionCart = cartSetup();
		homeModel.addObject("httpSession", httpSession);
		homeModel.addObject("cartService", cartServiceImpl);
		homeModel.addObject("sessionCart", sessionCart);
		homeModel.addObject("isUserLogged", isUserLogged());
		String username = SecurityContextHolder.getContext().getAuthentication().getName().split("@")[0];
		homeModel.addObject("username", username);
		httpSession.setAttribute("cartId", sessionCart.getCartId());

		return homeModel;
	}

	@GetMapping("/menu")
	public ModelAndView showMenu() {
		ModelAndView menuModel = new ModelAndView("site/menu");

		Cart sessionCart = cartSetup();

		menuModel.addObject("productService", productServiceImpl);
		menuModel.addObject("httpSession", httpSession);
		menuModel.addObject("cartService", cartServiceImpl);
		menuModel.addObject("sessionCart", sessionCart);
		menuModel.addObject("isUserLogged", isUserLogged());
		String username = SecurityContextHolder.getContext().getAuthentication().getName().split("@")[0];
		menuModel.addObject("username", username);
		httpSession.setAttribute("cartId", sessionCart.getCartId());

		return menuModel;
	}

	@GetMapping("/salades")
	public ModelAndView showSalades() {
		ModelAndView saladModel = new ModelAndView("site/salades");
		Cart sessionCart = cartSetup();
		Category saladCategory = categoryServiceImpl.findByCategoryName("Salades");
		saladModel.addObject("saladCategory", saladCategory);
		saladModel.addObject("productService", productServiceImpl);
		saladModel.addObject("httpSession", httpSession);
		saladModel.addObject("cartService", cartServiceImpl);
		saladModel.addObject("sessionCart", sessionCart);
		saladModel.addObject("isUserLogged", isUserLogged());
		String username = SecurityContextHolder.getContext().getAuthentication().getName().split("@")[0];
		saladModel.addObject("username", username);
		httpSession.setAttribute("cartId", sessionCart.getCartId());

		return saladModel;
	}

	@GetMapping("/pizzas")
	public ModelAndView showPizzas() {
		ModelAndView pizzaModel = new ModelAndView("site/pizzas");
		Cart sessionCart = cartSetup();
		Category pizzaCategory = categoryServiceImpl.findByCategoryName("Pizzas");
		pizzaModel.addObject("pizzaCategory", pizzaCategory);
		pizzaModel.addObject("productService", productServiceImpl);
		pizzaModel.addObject("httpSession", httpSession);
		pizzaModel.addObject("cartService", cartServiceImpl);
		pizzaModel.addObject("sessionCart", sessionCart);
		pizzaModel.addObject("isUserLogged", isUserLogged());
		String username = SecurityContextHolder.getContext().getAuthentication().getName().split("@")[0];
		pizzaModel.addObject("username", username);
		httpSession.setAttribute("cartId", sessionCart.getCartId());

		return pizzaModel;
	}

	@GetMapping("/hamburgers")
	public ModelAndView showBurgers() {
		ModelAndView burgerModel = new ModelAndView("site/burgers");
		Cart sessionCart = cartSetup();
		Category burgerCategory = categoryServiceImpl.findByCategoryName("Hamburgers");
		burgerModel.addObject("burgerCategory", burgerCategory);
		burgerModel.addObject("productService", productServiceImpl);
		burgerModel.addObject("httpSession", httpSession);
		burgerModel.addObject("cartService", cartServiceImpl);
		burgerModel.addObject("sessionCart", sessionCart);
		burgerModel.addObject("isUserLogged", isUserLogged());
		String username = SecurityContextHolder.getContext().getAuthentication().getName().split("@")[0];
		burgerModel.addObject("username", username);
		httpSession.setAttribute("cartId", sessionCart.getCartId());

		return burgerModel;
	}

	@GetMapping("/desserts")
	public ModelAndView showDessert() {
		ModelAndView dessertModel = new ModelAndView("site/desserts");

		Cart sessionCart = cartSetup();
		Category dessertCategory = categoryServiceImpl.findByCategoryName("Desserts");
		dessertModel.addObject("dessertCategory", dessertCategory);
		dessertModel.addObject("productService", productServiceImpl);
		dessertModel.addObject("httpSession", httpSession);
		dessertModel.addObject("cartService", cartServiceImpl);
		dessertModel.addObject("sessionCart", sessionCart);
		dessertModel.addObject("isUserLogged", isUserLogged());
		String username = SecurityContextHolder.getContext().getAuthentication().getName().split("@")[0];
		dessertModel.addObject("username", username);
		httpSession.setAttribute("cartId", sessionCart.getCartId());

		return dessertModel;
	}

	@GetMapping("/boissons")
	public ModelAndView showBoissons() {
		ModelAndView drinkModel = new ModelAndView("site/boissons");
		Cart sessionCart = cartSetup();
		Category drinkCategory = categoryServiceImpl.findByCategoryName("Boissons");
		drinkModel.addObject("drinkCategory", drinkCategory);
		drinkModel.addObject("productService", productServiceImpl);
		drinkModel.addObject("httpSession", httpSession);
		drinkModel.addObject("cartService", cartServiceImpl);
		drinkModel.addObject("sessionCart", sessionCart);
		drinkModel.addObject("isUserLogged", isUserLogged());
		String username = SecurityContextHolder.getContext().getAuthentication().getName().split("@")[0];
		drinkModel.addObject("username", username);
		httpSession.setAttribute("cartId", sessionCart.getCartId());

		return drinkModel;
	}

	@GetMapping("/plats")
	public ModelAndView showPlats() {
		ModelAndView dishModel = new ModelAndView("site/plats");

		Cart sessionCart = cartSetup();
		Category dishCategory = categoryServiceImpl.findByCategoryName("Plats");
		dishModel.addObject("dishCategory", dishCategory);
		dishModel.addObject("productService", productServiceImpl);
		dishModel.addObject("httpSession", httpSession);
		dishModel.addObject("cartService", cartServiceImpl);
		dishModel.addObject("sessionCart", sessionCart);
		dishModel.addObject("isUserLogged", isUserLogged());
		String username = SecurityContextHolder.getContext().getAuthentication().getName().split("@")[0];
		dishModel.addObject("username", username);
		httpSession.setAttribute("cartId", sessionCart.getCartId());

		return dishModel;
	}

	@GetMapping("/about-us")
	public ModelAndView showAbout() {
		ModelAndView aboutModel = new ModelAndView("site/about-us");
		aboutModel.addObject("categoryService", categoryServiceImpl);

		Cart sessionCart = cartSetup();
		aboutModel.addObject("httpSession", httpSession);
		aboutModel.addObject("cartService", cartServiceImpl);
		aboutModel.addObject("sessionCart", sessionCart);
		aboutModel.addObject("isUserLogged", isUserLogged());
		String username = SecurityContextHolder.getContext().getAuthentication().getName().split("@")[0];
		aboutModel.addObject("username", username);
		httpSession.setAttribute("cartId", sessionCart.getCartId());

		return aboutModel;
	}

	@GetMapping("/contact")
	public ModelAndView showContact() {
		ModelAndView contactModel = new ModelAndView("site/contact");

		Cart sessionCart = cartSetup();

		contactModel.addObject("productService", productServiceImpl);
		contactModel.addObject("httpSession", httpSession);
		contactModel.addObject("cartService", cartServiceImpl);
		contactModel.addObject("sessionCart", sessionCart);
		contactModel.addObject("isUserLogged", isUserLogged());
		String username = SecurityContextHolder.getContext().getAuthentication().getName().split("@")[0];
		contactModel.addObject("username", username);
		httpSession.setAttribute("cartId", sessionCart.getCartId());

		return contactModel;
	}

	public boolean isUserLogged() {
		return (SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken);
	}

	public Cart cartSetup() {
		if (isUserLogged()) {
			Cart sessionCart = cartServiceImpl
					.getLastUserCart(SecurityContextHolder.getContext().getAuthentication().getName());
			return sessionCart;
		} else {
			Cart sessionCart = cartServiceImpl.getCartByUserSessionId(httpSession.getId());
			if (sessionCart == null) {
				sessionCart = new Cart();
				sessionCart.setUser(null);
				sessionCart.setTokenSession(httpSession.getId());
				sessionCart.setActive(true);
				cartServiceImpl.saveOrUpdateCart(sessionCart);
			}
			return sessionCart;
		}
	}

}
