package sn.niit.restauranManagementApplication.service;

import java.util.List;

import sn.niit.restauranManagementApplication.domain.Order;
import sn.niit.restauranManagementApplication.domain.Produit;
import sn.niit.restauranManagementApplication.domain.User;


public interface OrderService 
{
	void saveOrUpdateOrder(Order order);
	Order getOrderById(Long id);
	 String getOrderState(Order order);
	void deleteOrder(Long Id);
	List<User> getAllUser();
    List<Produit> getAllProduits();
    List<Order> getAllOrders();
}
