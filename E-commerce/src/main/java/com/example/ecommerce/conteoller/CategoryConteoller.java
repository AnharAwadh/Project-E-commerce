package com.example.ecommerce.conteoller;

import com.example.ecommerce.model.Api;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/Category")

public class CategoryConteoller {
    private final CategoryService categoryService;


    @GetMapping
    public ResponseEntity<ArrayList<Category>> getCategory(){

        return ResponseEntity.status(200).body(categoryService.getCategorys());

    }
    @PostMapping
    public ResponseEntity<Api> addCategory(@RequestBody @Valid Category category, Errors errors) {

        if (errors.hasErrors()) {

            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }

        boolean isCategoryadd = categoryService.addCategory(category);
        if (!isCategoryadd) {
            return ResponseEntity.status(500).body(new Api("Error adding a Category", 500));

        }
        return ResponseEntity.status(200).body(new Api("New  Category added", 200));
    }
    @PutMapping("{index}")
    public ResponseEntity<Api> editCategory(@PathVariable int index, @RequestBody@Valid Category category, Errors errors){

        if (errors.hasErrors()){

            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }

        boolean isCategoryUpdate= categoryService.updateCategory(index,category);
        if (!isCategoryUpdate) {
            return ResponseEntity.status(500).body(new Api("Error Updating a Category", 500));

        }
        return ResponseEntity.status(200).body(new Api("New Category update", 200));
    }

    @DeleteMapping("{index}")
    public ResponseEntity<Api> deleteCart(@PathVariable int index){

       categoryService.deleteCategory(index);

        return ResponseEntity.status(200).body(new Api(" Category delete", 200));
    }
}
