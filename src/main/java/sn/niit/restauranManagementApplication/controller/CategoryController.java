package sn.niit.restauranManagementApplication.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
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
import sn.niit.restauranManagementApplication.domain.Category;
import sn.niit.restauranManagementApplication.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	CategoryService catService;

	@GetMapping("/list")
	public String showAllCategorie(Model model) {
		return showPaginatedPage(1, model);
	}

	@GetMapping("/new")
	public String showForm(Category categorie) {
		return "admin/categorie-new";
	}

	@RolesAllowed("ROLE_ADMIN")
	@PostMapping("/save")
	public String saveCategorie(@Valid Category categorie, BindingResult bindingResul) {
		if (bindingResul.hasErrors()) {
			return "admin/categorie-new";
		} else {
			catService.saveOrUpdateCategory(categorie);
			return "redirect:/categorie/list";
		}
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable("id") Long id, Model model) {
		Category categorie = catService.findById(id);

		model.addAttribute("categorie", categorie);
		return "admin/categorie-edit";
	}

	@PostMapping("/update/{id}")
	public String updateCategorie(@PathVariable("id") Long id, Category categorie) {
		catService.saveOrUpdateCategory(categorie);
		return "redirect:/categorie/list";
	}

	@GetMapping("/delete/{id}")
	public String deleteCategorie(@PathVariable("id") Long id) {
		catService.deleteCategorie(id);
		return "redirect:/categorie/list";
	}

	@GetMapping("/list/page/{pageNumber}")
	public String showPaginatedPage(@PathVariable("pageNumber") int pageNumber, Model model) {
		int pageSize = 5;
		Page<Category> page = catService.findPaginated(pageNumber, pageSize);
		List<Category> categorieList = page.getContent();

		model.addAttribute("pageCourente", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("categorieList", categorieList);
		model.addAttribute("page", page);

		return "admin/categorie-list";

	}

}
