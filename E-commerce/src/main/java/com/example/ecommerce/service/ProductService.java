package com.example.ecommerce.service;

import com.example.ecommerce.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final UserService userService;
    private final  MerchantService merchantService;
    private final MerchantStockService merchantStockService;
    private final PurchaseHistoryService purchaseHistoryService;
    private final CartService cartService;
    private ArrayList<Product> products=new ArrayList<>();


    public ArrayList<Product> getProducts(){

        return products;
    }
    public boolean addProducts(Product product){

        return products.add(product);

    }
    public Product deleteProducts(int index){

        return products.remove(index);
    }
    public boolean updateProducts(int indx, Product product) {
        if (indx == 0) {
            return false;

        }
        products.set(indx, product);
        return true;
    }

    public Product getProductid(String productid){

        for (Product product:products) {
            if (product.getId().equals(productid))
                return product;

        }
        return  null;
    }


    public Integer buy(String userid, String productid, String merchantId) {
        User currUser=userService.getUserid(userid);
        Product currProduct=getProductid(productid);
        Merchant currMerchant=merchantService.getMerchantById(merchantId);
        if (currUser==null){
            return -5;}
        if (currProduct==null){
            return -4;
        }
        if (currMerchant==null){
            return -3;
        }
        MerchantStock merchantStock = merchantStockService.getMerchantByProductIdAndMerchantID(currProduct.getId(), currMerchant.getId());
        if (merchantStock==null){

            return -2;
        }

        if (currUser.getBalance()<currProduct.getPrice()){
            return -1;}

        currUser.setBalance(currUser.getBalance()-currProduct.getPrice());
        merchantStock.setStock(String.valueOf(Integer.parseInt(merchantStock.getStock())-1));
        purchaseHistoryService.addPurchase(currUser,currProduct);
        return 0;


    }

    public Integer buyForCart(String userid) {
        User currentUser = userService.getUserid(userid);
        if (currentUser == null) {
            return  -1;
        }
        Cart cart = cartService.getWithoutCreateCartForUser(userid);
        if (cart == null) {
            return -2;
        }
        List<Product> products = new ArrayList(cart.getProducts().values());
        if(currentUser.getBalance() < getTotalPrice(products)) {
            return -3;
        }
        Boolean checkAvailableInStock = allProductsAvailableInStock(products);
        if(!checkAvailableInStock) {
            return -4;
        }
        for (Product product : products) {
            merchantStockService.reduceStock(product.getId());
            currentUser.setBalance(currentUser.getBalance() - product.getPrice());
            purchaseHistoryService.addPurchase(currentUser,product);
        }
            return 0;

    }
    private int getTotalPrice(List<Product> products) {
        int total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }
    private Boolean allProductsAvailableInStock(List<Product> products) {
        for (Product product : products) {
            boolean checkStock = merchantStockService.checkStock(product.getId());
            if(!checkStock) {
                return false;
            }

        }
        return true;
    }

    public void addComment(String productid, Comment comment) {
        Product currProduct=getProductid(productid);
        currProduct.getComments().add(comment);
    }

    public List<Comment> getAllComment(String productid) {
        Product productid1 = getProductid(productid);
        if (productid1 == null) {
            return null;
        }
        return productid1.getComments();
    }

    public List<Product> getRate5Product() {
        List<Product> rate5Products = new ArrayList<>();
        for (Product product : products) {
            int rate = getRate(product);
            if(rate == 5) {
                rate5Products.add(product);
            }
        }
        return rate5Products;
    }

    private int getRate(Product product) {
        int rate = 0;
        for (Comment comment : product.getComments()) {
            rate += comment.getRate();
        }
        if (product.getComments().size()!=0){
        return rate/product.getComments().size();}
        return 0;

    }

    public Integer userAddProduct(String userid, String productid) {
        User currentUser = userService.getUserid(userid);
        Product currentProduct = getProductid(productid);
        if (currentUser == null) {
            return -2;
        }
        if (currentProduct == null) {
            return -1;
        }
        Cart userCart = cartService.getOrCreateCartForUser(userid);
        cartService.addProductToCart(userCart,currentProduct);
        return 0;
    }
    public Integer userRmoveProduct(String userid, String productid) {
        User currentUser = userService.getUserid(userid);
        Product currentProduct = getProductid(productid);
        if (currentUser == null) {
            return -3;
        }
        if (currentProduct == null) {
            return -2;
        }

        Cart userCart = cartService.getWithoutCreateCartForUser(userid);
        cartService.removeProductFromCart(userCart,currentProduct);
        return 0;
    }
}
