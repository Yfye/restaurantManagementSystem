package sn.niit.restauranManagementApplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sn.niit.restauranManagementApplication.domain.Categorie;
import sn.niit.restauranManagementApplication.domain.Produit;
import sn.niit.restauranManagementApplication.service.CategorieService;
import sn.niit.restauranManagementApplication.service.OrderService;
import sn.niit.restauranManagementApplication.service.ProduitService;

@Controller
@RequestMapping("/produit")
public class ProduitController 
{
	    @Autowired 
	    ProduitService produitService;
	    OrderService ordertService;
	    @Autowired
	    CategorieService categorieService;

	@GetMapping("/list")
	public String showAllProduit(Model model,String keyword)
	{
		if(keyword!=null)
		{
			List<Produit> listProduit = produitService.getByKeyword(keyword);
			model.addAttribute("listProduit", listProduit);
		}else
		{
			List<Produit> listProduit = produitService.getAllProduit();
			model.addAttribute("listProduit", listProduit);
		}

		return showPaginatedPage(1, model);
	}
		
		@GetMapping("/new")
		public String showForm(Produit produit,  Model model)
		{
			model.addAttribute("categories", categorieService.getAllCategorie());
			return"admin/produit-new";
		}
		
		@PostMapping("/save")
		public String saveOrUpdate( @Valid Produit produit,BindingResult bindindResult )
		{
			if(bindindResult.hasErrors()) 
			{
				return "admin/produit-new";
			}
			else
		   {


			produitService.saveOrUpdateProduit(produit);
			return "redirect:/produit/list";
			}
		    
	    }
		
		@GetMapping("/edit/{id}")
		public String showEditForm(@PathVariable("id") Long id ,Model model) 
		{
			model.addAttribute("produit", produitService.getProduitById(id));
			model.addAttribute("categorie", categorieService.getAllCategorie());
			
			return "admin/produit-edit";
		}
		
		@PostMapping("/update/{id}")
		public String SaveOrUpdateProduct(@PathVariable("id") Long id, Produit produit)
		{
			produitService.saveOrUpdateProduit(produit);
			return "redirect:/produit/list";
		}
		
		@GetMapping("/delete/{id}")
		public String deleteProduit(@PathVariable("id") Long id) 
		{
			produitService.deleteProduit(id);
			return "redirect:/produit/list";
		}

	@GetMapping("/list/page/{pageNumber}")
	public String
	showPaginatedPage(@PathVariable("pageNumber") int pageNumber, Model model)
	{
		int pageSize=5;
		Page<Produit> page = produitService.findPaginated(pageNumber,pageSize);
		List<Produit>  produitList = page.getContent();

		model.addAttribute("pageCourente", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("produitList", produitList);
		model.addAttribute("page", page);

		return "admin/produit-list";

	}

}
