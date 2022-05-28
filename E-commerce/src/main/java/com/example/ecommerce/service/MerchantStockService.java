package com.example.ecommerce.service;


import com.example.ecommerce.model.Merchant;
import com.example.ecommerce.model.MerchantStock;
import com.example.ecommerce.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class MerchantStockService {

    private ArrayList<MerchantStock> merchantStocks=new ArrayList<>();
    @Autowired
    @Lazy
    private  ProductService productService;
    @Autowired
    private  MerchantService merchantService;


    public ArrayList<MerchantStock> getMerchantStock(){

        return merchantStocks;
    }
    public boolean addMerchantStock(MerchantStock merchantStock){

        return merchantStocks.add(merchantStock);

    }
    public MerchantStock  deleteMerchantStock(int index){

        return merchantStocks.remove(index);
    }
    public boolean updateMerchantStock(int indx, MerchantStock merchantStock) {
        if (indx == 0) {
            return false;

        }
      merchantStocks.set(indx,merchantStock);
        return true;
    }

    public MerchantStock getMerchantByProductIdAndMerchantID(String productId,String merchantId) {
        for (MerchantStock merchantStock:merchantStocks) {
            if (merchantStock.getProductid().equals(productId) && merchantStock.getMerchantid().equals(merchantId))
                return merchantStock;

        }
        return  null;
    }

    public Integer addProductToMerchantStock(String productId,String merchantId,Integer stock) {
        Product productid = this.productService.getProductid(productId);
        Merchant merchantById = this.merchantService.getMerchantById(merchantId);
        if(productid == null) {
            return -1;
        }
        if (merchantById == null) {
            return -2;
        }
        MerchantStock merchantByProductIdAndMerchantID = getMerchantByProductIdAndMerchantID(productId, merchantId);
        if (merchantByProductIdAndMerchantID == null) {
            return -3;
        }
        merchantByProductIdAndMerchantID.setStock(String.valueOf(Integer.parseInt(merchantByProductIdAndMerchantID.getStock()) + stock));
        return 0;
    }

    public boolean checkStock(String productId) {
        for (MerchantStock merchantStock : merchantStocks) {
            if(merchantStock.getProductid().equals(productId) && Integer.parseInt(merchantStock.getStock()) > 0) {
                return true;
            }
        }
        return false;
    }

    public void reduceStock(String productId) {
        for (MerchantStock merchantStock : merchantStocks) {
            if(merchantStock.getProductid().equals(productId)) {
                merchantStock.setStock(String.valueOf(Integer.parseInt(merchantStock.getStock()) + 1));
            }
        }
    }
}
