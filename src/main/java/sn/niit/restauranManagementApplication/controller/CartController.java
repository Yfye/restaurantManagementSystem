package sn.niit.restauranManagementApplication.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sn.niit.restauranManagementApplication.serviceImpl.CartServiceImpl;
import sn.niit.restauranManagementApplication.serviceImpl.ProductServiceImpl;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartServiceImpl cartServiceImpl;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    // public CartController(CartServiceImpl cartServiceImpl) {
    // this.cartServiceImpl = cartServiceImpl;
    // }

    @GetMapping("/addtocart/{cartId}/{productId}")
    public void addProductToCart(@PathVariable Long cartId, @PathVariable Long productId, HttpServletResponse response)
            throws IOException {
        System.out.println("Product " + productId + " added to cart " + cartId);
        cartServiceImpl.addProductToCart(cartId, productId);
        System.out.println(String.format("Item count: %d", cartServiceImpl.getCartById(cartId).getItemCount()));
        response.sendRedirect(
                String.format("/site/%s",
                        productServiceImpl.findById(productId).getCategory().getName().toLowerCase()));
    }

    @GetMapping("/removeItem/{cartId}/{productId}")
    public void removeProductFromCart(@PathVariable Long cartId, @PathVariable Long productId,
            HttpServletResponse response) throws IOException {
        System.out.println(String.format("Product %d about to be removed from cart %d.", productId, cartId));
        cartServiceImpl.removeProductFromCart(cartId, productId);
        response.sendRedirect("/site/menu");
    }

    @GetMapping("/validate/{cartId}")
    public void validateCart(@PathVariable Long cartId, HttpServletResponse response) throws IOException {
        cartServiceImpl.validateCart(cartId);
        response.sendRedirect("/site/menu");
    }

}
