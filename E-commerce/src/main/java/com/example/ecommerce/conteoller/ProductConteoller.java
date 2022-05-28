package com.example.ecommerce.conteoller;

import com.example.ecommerce.model.Api;
import com.example.ecommerce.model.Comment;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductConteoller {
    private final ProductService productService;


    @GetMapping
    public ResponseEntity<ArrayList<Product>> getProduct(){

        return ResponseEntity.status(200).body(productService.getProducts());

    }
    @PostMapping
    public ResponseEntity<Api> addProduct(@RequestBody @Valid Product product, Errors errors) {

        if (errors.hasErrors()) {

            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }

        boolean isProductadd = productService.addProducts(product);
        if (!isProductadd) {
            return ResponseEntity.status(500).body(new Api("Error adding a Product", 500));

        }
        return ResponseEntity.status(200).body(new Api("New Product added", 200));
    }
    @PutMapping("{index}")
    public ResponseEntity<Api> editProduct(@PathVariable int index,@RequestBody@Valid Product product, Errors errors){

        if (errors.hasErrors()){

            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }

        boolean isProductUpdate= productService.updateProducts(index,product);
        if (!isProductUpdate) {
            return ResponseEntity.status(500).body(new Api("Error Updateing a Product", 500));

        }
        return ResponseEntity.status(200).body(new Api("New Product update", 200));
    }

    @DeleteMapping("{index}")
    public ResponseEntity<Api> deleteProduct(@PathVariable int index){

      productService.deleteProducts(index);

        return ResponseEntity.status(200).body(new Api(" Product delete", 200));
    }

    @PostMapping("buy-direct")
    public ResponseEntity<Api> buyProductDirectly(@RequestParam String userid, @RequestParam String productid,@RequestParam  String merchantId){
      Integer buyProductDirectly=  productService.buy(userid,productid,merchantId);

        switch (buyProductDirectly) {
            case -5:
                return ResponseEntity.status(400).body(new Api("user id is not valid", 400));
            case -4:
                return ResponseEntity.status(400).body(new Api("product id is  not valid", 400));
            case -3:
                return ResponseEntity.status(400).body(new Api(" merchant id not valid", 400));
            case -2:
                return ResponseEntity.status(400).body(new Api(" merchant stock is not exit", 400));
            case -1:
                return ResponseEntity.status(400).body(new Api(" your balance is not enough", 400));

            case 0:
                return ResponseEntity.status(200).body(new Api("product buy", 200));
            default:
                return ResponseEntity.status(500).body(new Api("Server error", 500));
        }

    }


    @PostMapping("buy-cart")
    public ResponseEntity<Api> buyProductOnCart(@RequestParam String userid){
        Integer  buyProductOnCart=  productService.buyForCart(userid);

        switch ( buyProductOnCart) {
            case -4:
                return ResponseEntity.status(400).body(new Api("user id is not valid", 400));
            case -3:
                return ResponseEntity.status(400).body(new Api("Cart is not exit", 400));
            case -2:
                return ResponseEntity.status(400).body(new Api(" your balance is not enough", 400));
            case -1:
                return ResponseEntity.status(400).body(new Api(" Product is not exit", 400));
            case 0:
                return ResponseEntity.status(200).body(new Api("product buy", 200));

            default:
                return ResponseEntity.status(500).body(new Api("Server error", 500));
        }

    }
    @GetMapping("get-comment")
    public ResponseEntity<List<Comment>> getAllcomment(@RequestParam String productid){

        List<Comment> allComment = productService.getAllComment(productid);
        if (allComment == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.status(200).body(allComment);

    }
    @GetMapping("get-rate-5")
    public ResponseEntity<List<Product>> getAllRateOf5(){

        List<Product> rateof5 = productService.getRate5Product();

        return ResponseEntity.status(200).body(rateof5);

    }

}

