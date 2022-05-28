package com.example.ecommerce.conteoller;

import com.example.ecommerce.model.Api;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Merchant;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
@RestController
@RequestMapping("api/v1/merchant")
@RequiredArgsConstructor
public class MerchantConteoller {

    private final  MerchantService merchantService;


    @GetMapping
    public ResponseEntity<ArrayList<Merchant>> getMerchant(){

        return ResponseEntity.status(200).body(merchantService.getMerchant());

    }
    @PostMapping
    public ResponseEntity<Api> addMerchant(@RequestBody @Valid Merchant merchant , Errors errors) {

        if (errors.hasErrors()) {

            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }

        boolean isMerchantadd = merchantService.addMerchant(merchant);
        if (!isMerchantadd) {
            return ResponseEntity.status(500).body(new Api("Error adding aMerchant", 500));

        }
        return ResponseEntity.status(200).body(new Api("New Merchant added", 200));
    }
    @PutMapping("{index}")
    public ResponseEntity<Api> editMerchant(@PathVariable int index, @RequestBody@Valid Merchant merchant, Errors errors){

        if (errors.hasErrors()){

            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }

      boolean isMerchantUpdate= merchantService.updateMerchant(index,merchant);
        if (!isMerchantUpdate) {
            return ResponseEntity.status(500).body(new Api("Error Updating a Merchant", 500));


        }
        return ResponseEntity.status(200).body(new Api("New Merchant update", 200));
    }

    @DeleteMapping("{index}")
    public ResponseEntity<Api> deleteMerchant(@PathVariable int index){

       merchantService.deleteMerchant(index);

        return ResponseEntity.status(200).body(new Api(" Merchant delete", 200));
    }
}
