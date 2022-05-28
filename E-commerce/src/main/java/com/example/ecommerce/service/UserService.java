package com.example.ecommerce.service;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {

    private ArrayList<User> users = new ArrayList<>();


    public ArrayList<User> getUser() {

        return users;
    }

    public boolean addUser(User user) {

        return users.add(user);

    }

    public User deleteUser(int index) {

        return users.remove(index);
    }

    public boolean updateUser(int indx, User user) {
        if (indx == 0) {
            return false;

        }
        users.set(indx, user);
        return true;
    }


    public User getUserid(String userid) {

        for (User user : users) {
            if (user.getId().equals(userid))
                return user;

        }
        return null;
    }



}

