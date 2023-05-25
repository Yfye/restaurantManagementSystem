package sn.niit.restauranManagementApplication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sn.niit.restauranManagementApplication.domain.Category;
import sn.niit.restauranManagementApplication.domain.Order;
import sn.niit.restauranManagementApplication.service.OrderService;
import sn.niit.restauranManagementApplication.service.ProductService;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService orderService;

	@Autowired
	ProductService produitService;

	@GetMapping("/list")
	public String showOrderList(Model model) {
		model.addAttribute("orderService", orderService);
		return "admin/order-list";
	}

	@GetMapping("/new")
	public String createOrder(Model model) {
		model.addAttribute("users", orderService.getAllUser());
		model.addAttribute("produits", orderService.getAllProduits());
		model.addAttribute("order", new Order());
		return "admin/order-new";
	}

	@PostMapping("/save")
	public String saveOrder(@Valid Order order, BindingResult bindingResul) {
		if (bindingResul.hasErrors()) {
			return "admin/order-new";
		} else {
			orderService.saveOrUpdateOrder(order);
			return "redirect:/order/list";
		}
	}

}
