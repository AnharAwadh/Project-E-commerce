package com.example.ecommerce.service;

import com.example.ecommerce.model.Cart;

import com.example.ecommerce.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {
/* Map<K,V>
* An object that maps keys to values. A map cannot contain duplicate keys; each key can map to at most one value.
* */
    private Map<String,Cart> carts=new HashMap<>();


    public Set<Cart> getCarts(){

        return new HashSet<>(carts.values());
    }

    public Cart getOrCreateCartForUser(String userId) {
        Cart cart = carts.get(userId);
        if(cart == null) {
            Cart newCart = new Cart(userId);
            carts.put(userId,newCart);
            return newCart;
        }
        return cart;
    }
    public Cart getWithoutCreateCartForUser(String userId) {
        Cart cart = carts.get(userId);

        return cart;
    }

    public void addProductToCart(Cart cart, Product product) {

        cart.getProducts().put(product.getId(),product);
    }
    public void removeProductFromCart(Cart cart, Product product) {
        cart.getProducts().remove(product.getId());
    }

}
