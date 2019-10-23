package com.codecool.shop.dao.implementation;


import com.codecool.shop.model.UserAddress;

import java.util.ArrayList;
import java.util.List;

public class UserDaoMem {
    private List<UserAddress> users = new ArrayList<>();
    private static UserDaoMem instance = null;

    private UserDaoMem() {}

    public static UserDaoMem getInstance() {
        if (instance == null) {
            instance = new UserDaoMem();
        }
        return instance;
    }

    public void add(UserAddress user) {
        user.setId(users.size() + 1);
        users.add(user);
    }

    public UserAddress find(int id){
        return users.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public void remove(int id) {
        users.remove(find(id));
    }

    public List<UserAddress> getUsers() {
        return users;
    }
}
