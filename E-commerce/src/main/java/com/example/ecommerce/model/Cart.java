package com.example.ecommerce.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Data
public class Cart {
    private static int currentID = 0;
    @NotEmpty(message = " id required")
    @Size(min = 3,message = " id should be 3 character or more")
    private String id;
    @NotEmpty(message = "User id required")
    @Size(min = 3,message = "User id should be 3 character or more")
    private String  userid;
    private Map<String,Product> products;//use map

    public Cart(String userid) {
        this.id = String.valueOf(++currentID);
        this.userid = userid;
        this.products = new HashMap<>();
    }


}
