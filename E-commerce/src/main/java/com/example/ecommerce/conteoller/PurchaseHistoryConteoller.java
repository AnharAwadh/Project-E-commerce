package com.example.ecommerce.conteoller;

import com.example.ecommerce.model.Api;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Comment;
import com.example.ecommerce.model.PurchaseHistory;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.PurchaseHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/purchase")
public class PurchaseHistoryConteoller {

    private final PurchaseHistoryService purchaseHistoryService;


    @GetMapping
    public ResponseEntity<ArrayList<PurchaseHistory>> getCart(){

        return ResponseEntity.status(200).body(purchaseHistoryService.getPurchaseHistory());

    }

    @GetMapping("/get-allpruchase")
    public ResponseEntity<List<PurchaseHistory>> getAllPruchase(@RequestParam String userid){
        return ResponseEntity.ok(purchaseHistoryService.getAllPrush(userid));

    }

}
