package sn.niit.restauranManagementApplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sn.niit.restauranManagementApplication.domain.Product;
import sn.niit.restauranManagementApplication.service.CategoryService;
import sn.niit.restauranManagementApplication.service.OrderService;
import sn.niit.restauranManagementApplication.service.ProductService;

@Controller
@RequestMapping("/produit")
public class ProduitController {
	@Autowired
	ProductService produitService;
	OrderService ordertService;
	@Autowired
	CategoryService categorieService;

	@GetMapping("/list")
	public String showAllProduit(Model model, String keyword) {
		if (keyword != null) {
			List<Product> listProduit = produitService.findProductsByKeyword(keyword);
			model.addAttribute("listProduit", listProduit);
		} else {
			List<Product> listProduit = produitService.getProducts();
			model.addAttribute("listProduit", listProduit);
		}

		return showPaginatedPage(1, model);
	}

	@GetMapping("/new")
	public String showForm(Product produit, Model model) {
		model.addAttribute("categories", categorieService.getCategories());
		return "admin/produit-new";
	}

	@PostMapping("/save")
	public String saveOrUpdate(@Valid Product produit, BindingResult bindindResult) {
		if (bindindResult.hasErrors()) {
			return "admin/produit-new";
		} else {

			produitService.saveOrUpdateProduct(produit);
			return "redirect:/produit/list";
		}

	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("produit", produitService.findById(id));
		model.addAttribute("categorie", categorieService.getCategories());

		return "admin/produit-edit";
	}

	@PostMapping("/update/{id}")
	public String SaveOrUpdateProduct(@PathVariable("id") Long id, Product produit) {
		produitService.saveOrUpdateProduct(produit);
		return "redirect:/produit/list";
	}

	@GetMapping("/delete/{id}")
	public String deleteProduit(@PathVariable("id") Long id) {
		produitService.deleteProduit(id);
		return "redirect:/produit/list";
	}

	@GetMapping("/list/page/{pageNumber}")
	public String showPaginatedPage(@PathVariable("pageNumber") int pageNumber, Model model) {
		int pageSize = 5;
		Page<Product> page = produitService.findPaginated(pageNumber, pageSize);
		List<Product> produitList = page.getContent();

		model.addAttribute("pageCourente", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("produitList", produitList);
		model.addAttribute("page", page);

		return "admin/produit-list";

	}

}
