package com.example.ecommerce.service;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Merchant;
import com.example.ecommerce.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class MerchantService {

    private ArrayList<Merchant> merchants=new ArrayList<>();


    public ArrayList<Merchant> getMerchant(){

        return merchants;
    }
    public boolean addMerchant(Merchant merchant){

        return merchants.add(merchant);

    }
    public Merchant deleteMerchant(int index){

        return merchants.remove(index);
    }
    public boolean updateMerchant(int indx, Merchant merchant) {
        if (indx == 0) {
            return false;

        }
        merchants.set(indx,merchant);
        return true;
    }

    public Merchant getMerchantById(String merchantId) {
        for (Merchant merchant:merchants) {
            if (merchant.getId().equals(merchantId))
                return merchant;

        }
        return  null;
    }
}
