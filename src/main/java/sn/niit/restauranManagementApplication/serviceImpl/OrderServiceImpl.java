package sn.niit.restauranManagementApplication.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sn.niit.restauranManagementApplication.domain.Cart;
import sn.niit.restauranManagementApplication.domain.Order;
import sn.niit.restauranManagementApplication.domain.Product;
import sn.niit.restauranManagementApplication.domain.State;
import sn.niit.restauranManagementApplication.domain.User;
import sn.niit.restauranManagementApplication.repository.OrderRepository;
import sn.niit.restauranManagementApplication.repository.ProductRepository;
import sn.niit.restauranManagementApplication.repository.UserRepository;
import sn.niit.restauranManagementApplication.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderRepository orderRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductRepository produitRepository;

	@Override
	public void saveOrUpdateOrder(Order order) {
		orderRepository.save(order);
	}

	@Override
	public Order getOrderById(Long id) {
		Order order = null;
		Optional<Order> optionalOrder = orderRepository.findById(id);
		if (!optionalOrder.isEmpty()) {
			order = optionalOrder.get();
		} else {
			throw new RuntimeException("Aucune commande trouve");
		}
		return order;
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public Order getByCart(Cart cart) {
		return orderRepository.findByCart(cart);
	}

	@Override
	public void deleteOrder(Long Id) {
		orderRepository.deleteById(Id);
	}

	// @Override
	// public String getOrderState(Order order) {
	// return order.getState() ? String.valueOf(State.delievery) :
	// String.valueOf(State.pending);
	// }

	@Override
	public List<User> getAllUser() {

		return userRepository.findAll();

	}

	@Override
	public List<Product> getAllProduits() {

		return produitRepository.findAll();
	}

}
