package com.example.ecommerce.conteoller;

import com.example.ecommerce.model.Api;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/cart")
public class CartConteoller {
    private final CartService cartService;


    @GetMapping
    public ResponseEntity<Set<Cart>> getCart(){

        return ResponseEntity.status(200).body(cartService.getCarts());

    }

}
