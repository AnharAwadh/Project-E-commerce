package com.example.ecommerce.service;

import com.example.ecommerce.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class PurchaseHistoryService {
    private Integer incId  = 0;
    private ArrayList<PurchaseHistory> purchaseHistories=new ArrayList<>();


    public ArrayList<PurchaseHistory> getPurchaseHistory(){

        return purchaseHistories;
    }

    public void addPurchase(User currUser, Product currProduct) {
        purchaseHistories.add(new PurchaseHistory(String.valueOf(incId),currUser.getId(),currProduct.getId(),currProduct.getPrice()));
    }

    public PurchaseHistory getPurchase(String productid, String userid) {
        for (PurchaseHistory purchaseHistory : purchaseHistories) {
            if (purchaseHistory.getProductid().equals(productid) && purchaseHistory.getUserid().equals(userid)) {
                return purchaseHistory;
            }
        }
        return null;
    }


    public List<PurchaseHistory> getAllPrush(String userid) {
        List<PurchaseHistory> userPurchaseHistory = new ArrayList<>();
        for (PurchaseHistory purchaseHistory : purchaseHistories) {
            if((purchaseHistory.getUserid().equals(userid))) {
                userPurchaseHistory.add(purchaseHistory);
            }

        }
        return userPurchaseHistory;
    }


}
