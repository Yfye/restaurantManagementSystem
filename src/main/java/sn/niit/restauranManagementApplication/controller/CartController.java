package sn.niit.restauranManagementApplication.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import sn.niit.restauranManagementApplication.domain.Cart;
import sn.niit.restauranManagementApplication.serviceImpl.CartServiceImpl;
import sn.niit.restauranManagementApplication.serviceImpl.ProductServiceImpl;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartServiceImpl cartServiceImpl;
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping("/addtocart/{cartId}/{productId}")
    public void addProductToCart(@PathVariable Long cartId, @PathVariable Long productId, HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        System.out.println("Product " + productId + " added to cart " + cartId);
        cartServiceImpl.addProductToCart(cartId, productId);
        response.sendRedirect(
                String.format("/site/%s",
                        productServiceImpl.findById(productId).getCategory().getName().toLowerCase()));
    }

}
