package com.example.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class Category {


    @NotEmpty(message = " id required")
    @Size(min = 3,message = " id should be 3 character or more")
    private String id;
    @NotEmpty(message = " name required")
    @Size(min = 3,message = " name should be 3 character or more")
    private String name;
}
