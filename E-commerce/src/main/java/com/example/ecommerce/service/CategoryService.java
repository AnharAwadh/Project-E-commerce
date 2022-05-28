package com.example.ecommerce.service;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class CategoryService {

    private ArrayList<Category> categories=new ArrayList<>();


    public ArrayList<Category> getCategorys(){

        return categories;
    }
    public boolean addCategory(Category category){

        return categories.add(category);

    }
    public Category deleteCategory(int index){

        return categories.remove(index);
    }
    public boolean updateCategory(int indx, Category categoryt) {
        if (indx == 0) {
            return false;

        }
       categories.set(indx,categoryt);
        return true;
    }
}
