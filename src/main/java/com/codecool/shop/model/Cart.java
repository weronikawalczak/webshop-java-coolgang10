package com.codecool.shop.model;

import java.util.HashMap;
import java.util.Map;

public class Cart{
    private static Cart instance = null;
    Map<Product, Integer> data = new HashMap<>();
    private double sum;

    private Cart() {}

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void add(Product product) {
        if (data.containsKey(product)){
            int count = data.get(product) + 1;
            data.replace(product, count);
        }else {
            data.put(product, 1);
        }
        sum += product.getDefaultPrice();
    }

    public void subtract(Product product) {
        if (data.containsKey(product) && data.get(product) > 1){
            data.put(product, data.get(product) - 1);
            sum -= product.getDefaultPrice();
        }else if(data.containsKey(product)){
            remove(product.getId());
        }
    }

    public void changeQuantity(int quantity){

    }
    
    public Product find(int id) {
        for(Product product: data.keySet()){
            if(product.id == id){
                return product;
            }
        }
        return null;
    }
    
    public void remove(int id) {
        sum -= find(id).getDefaultPrice() * data.get(find(id));
        data.remove(find(id));
    }

    public Map<Product, Integer> getAll() {
        return data;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
