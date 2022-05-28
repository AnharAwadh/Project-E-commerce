package com.example.ecommerce.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
public class Product {
    private static int currentID = 0;
    @NotEmpty(message = "Your id required")
    @Size(min = 3,message = "Your id should be 3 character or more")
    private String id;
    @NotEmpty(message = "Your name required")
    @Size(min = 3,message = "Your name should be 3 character or more")
    private String name;
    @NotNull(message ="price required" )
    private Integer price;
    @NotEmpty(message = "category id required")
    @Size(min = 3,message = "The category should be 3 character or more")
    private String categoryid;
    private ArrayList<Comment> comments;

    public Product(String name, Integer price, String categoryid) {
        this.id = String.valueOf(++currentID);;
        this.name = name;
        this.price = price;
        this.categoryid = categoryid;
        this.comments =new ArrayList<>();
    }
}
