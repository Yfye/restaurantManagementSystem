package sn.niit.restauranManagementApplication.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "line_items")
public class LineItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productId", referencedColumnName = "productId")
	private Product product;

	@NotNull
	private int quantite;

	@Temporal(TemporalType.DATE)
	private Date date;

	public LineItem() {
	}

	public LineItem(Product product, int quantite) {
		this.product = product;
		this.quantite = quantite;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product produit) {
		this.product = produit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LineItem lineItem = (LineItem) o;
		return id == lineItem.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
