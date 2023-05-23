package sn.niit.restauranManagementApplication.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Categorie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "Le nom de la catégorie est obligatoire")
	@NotEmpty(message = "Le nom de la catégorie est obligatoire ")
	private String nom;

	private String description;

	@Column
	private String image;

	@Column
	private String vector;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "categorie")
	List<Produit> listProduit;

	public Categorie() {
	}

	public Categorie(String nom, String description, String image, String vector) {
		this.nom = nom;
		this.image = (image.length() > 0) ? image : "/site/images/blank_category.jpg";
		this.description = description;
		this.vector = vector;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "CategorieService [id=" + id + ", nom=" + nom + ", description=" + description + "]";
	}

	public String getVector() {
		return vector;
	}

	public void setVector(String vector) {
		this.vector = vector;
	}

	public List<Produit> getListProduit() {
		return listProduit;
	}

	public void setListProduit(List<Produit> listProduit) {
		this.listProduit = listProduit;
	}

}
