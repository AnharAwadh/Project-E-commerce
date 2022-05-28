package com.example.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.ArrayList;

@Data
@AllArgsConstructor
public class User {

    @NotEmpty(message = " id required")
    @Size(min = 3,message = " id should be 3 character or more")
    private String id;
    @NotEmpty(message = " User name required")
    @Size(min = 5,message = " user name should be 5 character or more")
    private String username;
    @NotEmpty(message = " password required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$",message = "Your password should be  contains uppercase character and lowercase character  at least 6 characters ")
    private String password;
    @NotEmpty(message = " email required")
    @Email(message = "Please inter the valid Email ")
    private String email;
    @NotEmpty(message = " role required")
    @Pattern(regexp = "Admin|Customer",message = " you should be admin or customer")
    private String role;
    @NotNull(message = "balance required")
    @Positive(message = "Your balance should be Positive")
    private Integer balance;

}
