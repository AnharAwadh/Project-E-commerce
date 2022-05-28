package com.example.ecommerce.service;

import com.example.ecommerce.model.Comment;
import com.example.ecommerce.model.PurchaseHistory;
import com.example.ecommerce.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
@RequiredArgsConstructor
public class CommentService {

    private ArrayList<Comment> comments=new ArrayList<>();
    private  final PurchaseHistoryService purchaseHistoryService;
    private final ProductService productService;


    public ArrayList<Comment> getComment(){

        return comments;
    }
    public boolean addComment1(Comment comment) {

        return comments.add(comment);

    }
    public boolean addComment(String userid, String productid, Comment comment){
        PurchaseHistory purchase = purchaseHistoryService.getPurchase(productid, userid);
        if(purchase == null) {
            return false;
        }

        productService.addComment(productid,comment);
        return true;
    }
    public Comment deleteComment(int index){

        return comments.remove(index);
    }
    public boolean updateComment(int indx, Comment comment) {
        if (indx == 0) {
            return false;

        }
        comments.set(indx,comment);
        return true;
    }
}
