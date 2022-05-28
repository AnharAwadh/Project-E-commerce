package com.example.ecommerce.conteoller;

import com.example.ecommerce.model.Api;
import com.example.ecommerce.model.Merchant;
import com.example.ecommerce.model.MerchantStock;
import com.example.ecommerce.service.MerchantService;
import com.example.ecommerce.service.MerchantStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/stock")
public class MerchantStockConteoller {

    private final MerchantStockService merchantStockService;
    @GetMapping
    public ResponseEntity<ArrayList<MerchantStock>> getStock(){

        return ResponseEntity.status(200).body(merchantStockService.getMerchantStock());

    }
    @PostMapping
    public ResponseEntity<Api> addStock(@RequestBody @Valid MerchantStock merchantStock , Errors errors) {

        if (errors.hasErrors()) {

            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }

        boolean isMerchantadd = merchantStockService.addMerchantStock(merchantStock);
        if (!isMerchantadd) {
            return ResponseEntity.status(500).body(new Api("Error adding a Stock", 500));

        }
        return ResponseEntity.status(200).body(new Api("New Stockt added", 200));
    }
    @PutMapping("{index}")
    public ResponseEntity<Api> editStock(@PathVariable int index, @RequestBody@Valid MerchantStock merchantStock, Errors errors){

        if (errors.hasErrors()){

            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(),400));
        }

        boolean isMerchantStockUpdate= merchantStockService.updateMerchantStock(index,merchantStock);
        if (!isMerchantStockUpdate) {
            return ResponseEntity.status(500).body(new Api("Error Updating a Stockt", 500));


        }
        return ResponseEntity.status(200).body(new Api("New Stock update", 200));
    }

    @DeleteMapping("{index}")
    public ResponseEntity<Api> deleteStock(@PathVariable int index){

        merchantStockService.deleteMerchantStock(index);

        return ResponseEntity.status(200).body(new Api(" Stock delete", 200));
    }
    @PutMapping("/addProduct")
    public ResponseEntity<Api> addProductTostock(@RequestParam String userid, @RequestParam String merchantId,@RequestParam Integer stcok) {
        Integer addProductTostock = merchantStockService.addProductToMerchantStock(userid,merchantId,stcok);
        switch (addProductTostock) {
            case -1:
                return ResponseEntity.status(400).body(new Api("product id is not valid", 400));
            case -2:
                return ResponseEntity.status(400).body(new Api("merchant id is  not valid", 400));
            case -3:
                return ResponseEntity.status(400).body(new Api(" merchant stock is not exit", 400));

            case 0:
                return ResponseEntity.status(200).body(new Api("product add merchant to stock", 200));
            default:
                return ResponseEntity.status(500).body(new Api("Server error", 500));
        }

    }
}
