package com.example.ecommerce.conteoller;

import com.example.ecommerce.model.Api;
import com.example.ecommerce.model.MerchantStock;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.MerchantStockService;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserConteoller {

    private final UserService userService;
    private final ProductService productService;
    @GetMapping
    public ResponseEntity<ArrayList<User>> getUser(){

        return ResponseEntity.status(200).body(userService.getUser());

    }
    @PostMapping
    public ResponseEntity<Api> addUser(@RequestBody @Valid User user, Errors errors) {

        if (errors.hasErrors()) {

            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }

        boolean isUseradd = userService.addUser(user);
        if (!isUseradd ) {
            return ResponseEntity.status(500).body(new Api("Error User a Stock", 500));

        }
        return ResponseEntity.status(200).body(new Api("New User added", 200));
    }
    @PutMapping("{index}")
    public ResponseEntity<Api> editUser(@PathVariable int index, @RequestBody@Valid User user, Errors errors){

        if (errors.hasErrors()){

            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }

        boolean isUserUpdate= userService.updateUser(index,user);
        if (! isUserUpdate) {
            return ResponseEntity.status(500).body(new Api("Error Updating a User", 500));


        }
        return ResponseEntity.status(200).body(new Api("New Stock User", 200));
    }

    @DeleteMapping("{index}")
    public ResponseEntity<Api> deleteUser(@PathVariable int index){

      userService.deleteUser(index);

        return ResponseEntity.status(200).body(new Api(" User delete", 200));
    }
    @PutMapping("/addprodct")
    public ResponseEntity<Api> addProduct(@RequestParam String userid, @RequestParam String productid) {
        Integer addProduct = productService.userAddProduct(userid, productid);
        switch (addProduct) {
            case -2:
                return ResponseEntity.status(400).body(new Api("user id is not valid", 400));
            case -1:
                return ResponseEntity.status(400).body(new Api("product id is  not valid", 400));
            case 0:
                return ResponseEntity.status(200).body(new Api("product add", 200));

            default:
                return ResponseEntity.status(500).body(new Api("Server error", 500));
        }

    }
    @PutMapping("/removeprodct")
    public ResponseEntity<Api> removeProduct(@RequestParam String userid, @RequestParam String productid) {
        Integer removeProduct = productService.userRmoveProduct(userid, productid) ;
        switch (removeProduct) {
            case -3:
                return ResponseEntity.status(400).body(new Api("user id is not valid", 400));
            case -2:
                return ResponseEntity.status(400).body(new Api("product id is  not valid", 400));
            case 0:
                return ResponseEntity.status(200).body(new Api("product remove", 200));

            default:
                return ResponseEntity.status(500).body(new Api("Server error", 500));
        }

    }
}
