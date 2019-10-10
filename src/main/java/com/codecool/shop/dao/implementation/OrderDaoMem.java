package com.codecool.shop.dao.implementation;


import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;

import java.util.ArrayList;
import java.util.List;

public class OrderDaoMem {
    private List<Order> orders = new ArrayList<>();
    private static OrderDaoMem instance = null;

    private OrderDaoMem() {}

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }

    public void add(Order order) {
        order.setId(orders.size() + 1);
        orders.add(order);
    }

    public Order find(int id){
        return orders.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    public void remove(int id) {
        orders.remove(find(id));
    }

    public List<Order> getOrders() {
        return orders;
    }
}
