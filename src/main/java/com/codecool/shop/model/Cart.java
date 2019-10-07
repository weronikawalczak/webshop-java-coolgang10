package com.codecool.shop.model;

public class Cart{
    private static Cart instance = null;

    private Cart() {}

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }
}
