package sn.niit.restauranManagementApplication.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.niit.restauranManagementApplication.domain.Cart;
import sn.niit.restauranManagementApplication.domain.LineItem;
import sn.niit.restauranManagementApplication.domain.Product;
import sn.niit.restauranManagementApplication.domain.User;
import sn.niit.restauranManagementApplication.repository.CartRepository;
import sn.niit.restauranManagementApplication.service.CartService;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private CartServiceImpl cartServiceImpl;

    @Autowired
    private LineItemServiceImpl lineItemServiceImpl;

    @Override
    public void saveOrUpdateCart(Cart cart) {
        try {
            User cartUser = userServiceImpl.findByUserId(cart.getUser().getUserId())
                    .orElseThrow(() -> new RuntimeException("User does not exist !"));
        } catch (RuntimeException e) {
            cart.setUser(null);
        }
        cartRepository.save(cart);
    }

    @Override
    public void addProductToCart(Long cartId, Long productId) {
        Cart cart = cartServiceImpl.getCartById(cartId);
        Product product = productServiceImpl.findById(productId);
        if (cart == null || product == null) {
            throw new RuntimeException("Error: Cannot add product to cart.");
        }
        for (LineItem lineItem : this.getCartById(cartId).getItems()) {
            if (doesProductExistInCart(cartId, lineItem.getProduct().getProductId())) {
                lineItem.setQuantite(lineItem.getQuantite() + 1);
                // lineItemServiceImpl
            }
        }

    }

    public boolean doesProductExistInCart(Long cartId, Long productId) {
        Cart cart = this.getCartById(cartId);
        Product product = productServiceImpl.findById(productId);
        for (LineItem lineItem : cart.getItems())
            if (lineItem.getProduct().getName().equals(product.getName()))
                return true;
        return false;
    }

    @Override
    public Cart getCartById(Long id) {
        Cart Cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Panier non trouvé"));

        return Cart;
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        User user = userServiceImpl.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User does not exist !"));
        return cartRepository.findByUser(user);
    }

    @Override
    public Cart getCartByUserSessionId(String userSessionId) {
        return cartRepository.findBytokenSession(userSessionId);
    }

    @Override
    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }
}
