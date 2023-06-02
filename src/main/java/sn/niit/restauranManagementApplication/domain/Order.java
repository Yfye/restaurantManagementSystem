package sn.niit.restauranManagementApplication.domain;

import java.time.LocalDate;

import javax.persistence.*;

@Entity(name = "orders")
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;

	private LocalDate date;

	private double totalPrice;

	private Boolean state;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cartId", referencedColumnName = "cartId")
	private Cart cart;

	public Order() {
	}

	public Order(LocalDate date) {
		this.date = date;

	}

	public long getId() {
		return orderId;
	}

	public void setId(long id) {
		this.orderId = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Order [id=" + orderId + ", date=" + date + ", totalPrice=" + totalPrice + ", state=" + state;
	}

}
