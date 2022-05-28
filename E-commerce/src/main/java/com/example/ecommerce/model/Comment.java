package com.example.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class Comment {

    @NotEmpty(message = " id required")
    @Size(min = 3,message = " id should be 3 character or more")
    private String id ;
    @NotEmpty(message = " User id  required")
    @Size(min = 3,message = " user id should be 5 character or more")
    private String userid ;
    @NotEmpty(message = " Message required")
    @Size(min = 6,message = " Message should be 6 character or more")
    private String message;
    @NotNull(message = " Rate required")
    @Min(value = 1,message = "min value mast be more then 0")
    @Max(value = 5,message = "max value mast less then 6")
    private Integer rate;
}
