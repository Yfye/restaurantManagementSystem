package sn.niit.restauranManagementApplication.service;

import sn.niit.restauranManagementApplication.domain.Cart;
import sn.niit.restauranManagementApplication.domain.User;

import java.util.List;

public interface CartService {

    void saveOrUpdateCart(Cart cart);

    void addProductToCart(Long cartId, Long productId);

    Cart getCartById(Long id);

    Cart getCartByUserId(Long userId);

    Cart getCartByUserSessionId(String userSessionId);

    List<Cart> getAllCart();

}
