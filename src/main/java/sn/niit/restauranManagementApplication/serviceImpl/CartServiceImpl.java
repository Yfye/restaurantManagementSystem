package sn.niit.restauranManagementApplication.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.niit.restauranManagementApplication.domain.Cart;
import sn.niit.restauranManagementApplication.repository.CartRepository;
import sn.niit.restauranManagementApplication.service.CartService;

import java.util.List;
import java.util.Optional;
@Service
public class CartServiceImpl implements CartService
{
    @Autowired
    CartRepository cartRepository;
    @Override
    public void saveOrUpdateCart(Cart cart)
    {
        cartRepository.save(cart);

    }

    @Override
    public Cart getCartById(Long id)
    {
        Cart Cart=  cartRepository.findById(id).orElseThrow( ()-> new RuntimeException("Panier non trouvé"));

        return Cart;
    }

    @Override
    public List<Cart> getAllCart()
    {
        return cartRepository.findAll();
    }
}
