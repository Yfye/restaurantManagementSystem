package sn.niit.restauranManagementApplication.service;

import java.util.List;

import sn.niit.restauranManagementApplication.domain.Order;
import sn.niit.restauranManagementApplication.domain.Product;
import sn.niit.restauranManagementApplication.domain.User;

public interface OrderService {
	void saveOrUpdateOrder(Order order);

	Order getOrderById(Long id);

	String getOrderState(Order order);

	void deleteOrder(Long Id);

	List<User> getAllUser();

	List<Product> getAllProduits();

	List<Order> getAllOrders();
}
