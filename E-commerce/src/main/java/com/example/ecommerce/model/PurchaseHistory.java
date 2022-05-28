package com.example.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class PurchaseHistory {
    @NotEmpty(message = " id required")
    @Size(min = 3,message = " id should be 3 character or more")
    private  String id;
    @NotEmpty(message = " User id required")
    @Size(min = 3,message = " User id should be 3 character or more")
    private String userid;
    @NotEmpty(message = " Product id required")
    @Size(min = 3,message = " Product id should be 3 character or more")
    private String productid;
    @NotNull(message = " Price required")
   @Positive(message = "must be positive ")
    private Integer price;
}
