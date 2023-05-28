package sn.niit.restauranManagementApplication.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.Objects;

@Entity
public class LineItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long lineItemId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "productId", referencedColumnName = "productId")
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cartId", referencedColumnName = "cartId")
	private Cart cart;

	@NotNull
	private int quantity;

	@Temporal(TemporalType.DATE)
	private Date date;

	public LineItem() {
	}

	public LineItem(Cart cart, Product product, int quantity) {
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;

	}

	public long getLineItemId() {
		return lineItemId;
	}

	public void setLineItemId(long lineItemId) {
		this.lineItemId = lineItemId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product produit) {
		this.product = produit;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
		return lineItemId == lineItem.lineItemId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(lineItemId);
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
}
