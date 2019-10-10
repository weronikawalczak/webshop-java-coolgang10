package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.ProductDaoMem;

import java.util.HashMap;
import java.util.Map;

public class Order {
    Map<Product, Integer> products = new HashMap<>();
    private ProductDaoMem productDaoMem = ProductDaoMem.getInstance();
    private String status;
    private int id;
    private double price;

    public Order(Map<String, String[]> products) {
        for (Map.Entry<String, String[]> product: products.entrySet()) {
            this.products.put(productDaoMem.find(Integer.parseInt(product.getKey())), Integer.parseInt(product.getValue()[0]));
        }
        this.price=countPrice();
        this.status = "unpaid";
    }

    private double countPrice(){
        double price = 0;

        for(Map.Entry<Product, Integer> product: this.products.entrySet()){
            price += (product.getKey().getDefaultPrice() * product.getValue());
        }
        return price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }
}
