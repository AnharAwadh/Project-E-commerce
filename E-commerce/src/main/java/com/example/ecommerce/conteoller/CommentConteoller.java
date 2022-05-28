package com.example.ecommerce.conteoller;

import com.example.ecommerce.model.Api;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Comment;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.CommentService;
import com.example.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/comment")
public class CommentConteoller {

    private final CommentService commentService;


    @GetMapping
    public ResponseEntity<ArrayList<Comment>> getComment(){

        return ResponseEntity.status(200).body(commentService.getComment());


    }

    @PostMapping("/add-comment")
    public ResponseEntity<Api> addComment(@RequestParam String userid,@RequestParam String productid,@RequestBody Comment comment){
        boolean added = commentService.addComment(userid, productid, comment);
        if(!added) {
            return ResponseEntity.badRequest().body(new Api("comment not add",400));
        }
        return ResponseEntity.ok(new Api("comment added ",200));
    }

}
