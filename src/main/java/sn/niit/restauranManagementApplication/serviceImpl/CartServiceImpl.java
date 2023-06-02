package sn.niit.restauranManagementApplication.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.niit.restauranManagementApplication.domain.Cart;
import sn.niit.restauranManagementApplication.domain.LineItem;
import sn.niit.restauranManagementApplication.domain.Order;
import sn.niit.restauranManagementApplication.domain.Product;
import sn.niit.restauranManagementApplication.domain.State;
import sn.niit.restauranManagementApplication.domain.User;
import sn.niit.restauranManagementApplication.repository.CartRepository;
import sn.niit.restauranManagementApplication.service.CartService;

import java.util.ConcurrentModificationException;
import java.util.List;

import javax.servlet.http.HttpSession;

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

    @Autowired
    private HttpSession httpSession;

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
        try {
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
        } catch (ConcurrentModificationException e) {
            // ! DO NOTHING!!
        }

    }

    public void removeProductFromCart(Long cartId, Long productId) {
        Cart cart = this.getCartById(cartId);
        List<LineItem> cartLineItems = cart.getLineItems();
        Product product = productServiceImpl.findById(productId);

        if (cart == null || product == null) {
            throw new RuntimeException("Error: Cannot remove product from cart.");
        }
        cartLineItems.removeIf(lineItem -> lineItem.getProduct().getName() == product.getName());
        cart.setLineItems(cartLineItems);
        cartRepository.save(cart);
    }

    public boolean doesSessionCartExist() {
        return this.getCartByUserSessionId(httpSession.getId()) != null;
    }

    public void assignSessionCartToUser(String email) {
        Cart sessionCart = this.getCartByUserSessionId(httpSession.getId());
        sessionCart.setUser(userServiceImpl.findUserByEmail(email));
        sessionCart.setTokenSession("");
        cartRepository.save(sessionCart);
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
    public Cart getLastUserCart(String email) {
        List<Cart> activeCarts = cartRepository.findByUser(userServiceImpl.findUserByEmail(email));
        activeCarts.removeIf(cart -> cart.getState().equals(State.INACTIVE));
        try {
            return activeCarts.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

    }

    @Override
    public Cart getCartByUserId(Long userId) {
        User user = userServiceImpl.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User does not exist !"));
        List<Cart> userCarts = cartRepository.findByUser(user);
        userCarts.removeIf(cart -> cart.getState().equals(State.ACTIVE) || cart.getState().equals(State.PENDING));
        try {
            return userCarts.get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public Cart getCartByUserSessionId(String userSessionId) {
        return cartRepository.findByTokenSession(userSessionId);
        // sessionCarts.removeIf(cart -> !cart.getState().equals(State.INACTIVE));
        // return sessionCarts.get(0);
    }

    @Override
    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    public void validateCart(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart does not exist!"));
        cart.setState(cart.getState().equals(State.ACTIVE) ? State.PENDING : State.ACTIVE);
        cartRepository.save(cart);
    }

    public boolean isCartActive(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart does not exist!"));
        return cart.getState().equals(State.ACTIVE);
    }

    public boolean isCartPending(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart does not exist!"));
        return cart.getState().equals(State.PENDING);
    }

    public boolean isCartEmpty(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart does not exist!"));
        if (cart == null)
            return true;
        boolean itemStatus = false;
        try {
            itemStatus = !(cart.getLineItems().size() > 0);
        } catch (NullPointerException e) {
            itemStatus = true;
        }
        return itemStatus;
    }

}
