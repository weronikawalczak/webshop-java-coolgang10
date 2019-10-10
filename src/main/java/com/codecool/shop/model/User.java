package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    int id;
    String name;
    String username;
    String email;
    String phoneNumber;
    Address shippingAddress;
    Address billingAddress;
    List<Order> orders;

    public User(String name, String username, String email, String phoneNumber, Address shippingAddress, Address billingAddress) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
