package com.example.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class MerchantStock {
    @NotEmpty(message = " id required")
    @Size(min = 3,message = " id should be 3 character or more")
    private String id;
    @NotEmpty(message = " product required")
    @Size(min = 3,message = " product should be 3 character or more")
    private String  productid;
    @NotEmpty(message = " merchant required")
    @Size(min = 3,message = " merchant should be 3 character or more")
    private String  merchantid;
    @NotEmpty(message = " Stock required")
    @Min(value = 10,message = "  Stock should be 10 or more")
    private String  stock;


}
