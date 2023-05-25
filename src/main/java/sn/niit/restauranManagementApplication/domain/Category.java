package sn.niit.restauranManagementApplication.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long categoryId;

	@NotNull(message = "Le nom de la catégorie est obligatoire")
	@NotEmpty(message = "Le nom de la catégorie est obligatoire ")
	private String name;

	private String description;

	@Column
	private String categoryImage;

	@Column
	private String vector;

	public Category() {
	}

	public Category(String name, String description, String categoryImage, String vector) {
		this.name = name;
		this.categoryImage = (categoryImage.length() > 0) ? categoryImage : "/site/images/blank_category.jpg";
		this.description = description;
		this.vector = vector;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategoryImage() {
		return categoryImage;
	}

	public void setCategoryImage(String categoryImage) {
		this.categoryImage = categoryImage;
	}

	@Override
	public String toString() {
		return "CategoryService [id=" + categoryId + ", name=" + name + ", description=" + description + "]";
	}

	public String getVector() {
		return vector;
	}

	public void setVector(String vector) {
		this.vector = vector;
	}

}
