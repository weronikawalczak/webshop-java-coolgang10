package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDaoMem {
    private List<User> users = new ArrayList<>();
    private static UserDaoMem instance = null;

    private UserDaoMem() {}

    public static UserDaoMem getInstance() {
        if (instance == null) {
            instance = new UserDaoMem();
        }
        return instance;
    }

    public void add(User user) {
        user.setId(users.size() + 1);
        users.add(user);
    }

    public User find(int id){
        return users.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public void remove(int id) {
        users.remove(find(id));
    }

    public List<User> getUsers() {
        return users;
    }
}
