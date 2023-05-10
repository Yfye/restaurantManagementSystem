package sn.niit.restauranManagementApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import sn.niit.restauranManagementApplication.domain.Categorie;
import sn.niit.restauranManagementApplication.domain.Produit;

public interface ProduitService 
{
	void saveOrUpdateProduit(Produit produit);
	Produit getProduitById(Long id);
	List<Produit>getAllProduit();

	List<Produit> getByKeyword(String keyword);

	void deleteProduit(Long id);
	Page<Produit> findPaginated(int pageNumber, int pageSize);



}
