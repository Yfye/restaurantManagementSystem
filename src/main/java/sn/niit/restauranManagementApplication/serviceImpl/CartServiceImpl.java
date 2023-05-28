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

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private LineItemServiceImpl lineItemServiceImpl;

    @Override
    public void saveOrUpdateCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void addProductToCart(Long cartId, Long productId) {
        Cart cart = this.getCartById(cartId);
        List<LineItem> cartLineItems = cart.getLineItems();
        Product product = productServiceImpl.findById(productId);
        LineItem lineItem = new LineItem(cart, product, 1);

        if (cart == null || product == null) {
            throw new RuntimeException("Error: Cannot add product to cart.");
        }

        boolean exist = doesProductExistInCart(cartId, lineItem.getProduct().getProductId());
        if (exist) {
            for (LineItem lineItemItrtr : cartLineItems)
                if (lineItemItrtr.getProduct().getName() == lineItem.getProduct().getName()) {
                    lineItemItrtr.setQuantity(lineItemItrtr.getQuantity() + 1);
                    lineItemServiceImpl.saveOrUpdateLineItem(lineItemItrtr);
                    cartRepository.save(cart);
                }

        } else {
            cartLineItems.add(lineItem);
            cart.setLineItems(cartLineItems);
            lineItemServiceImpl.saveOrUpdateLineItem(lineItem);
            cartRepository.save(cart);
        }

    }

    public boolean doesProductExistInCart(Long cartId, Long productId) {
        Cart cart = this.getCartById(cartId);
        Product product = productServiceImpl.findById(productId);
        for (LineItem lineItem : cart.getLineItems())
            if (lineItem.getProduct().getName() == product.getName())
                return true;
        return false;
    }

    @Override
    public Cart getCartById(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Panier non trouvé"));
        return cart;
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
