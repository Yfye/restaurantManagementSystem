package sn.niit.restauranManagementApplication.service;
import sn.niit.restauranManagementApplication.domain.Cart;

import java.util.List;

public interface CartService
{

    void saveOrUpdateCart(Cart cart);
    Cart getCartById(Long id);
    List<Cart> getAllCart();

}
