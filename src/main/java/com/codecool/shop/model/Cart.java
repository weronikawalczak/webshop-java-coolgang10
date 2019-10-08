package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart{
    private static Cart instance = null;
    Map<Product, Integer> data = new HashMap<>();
    private int numberOfProducts;
    private float sum = 0;

    private Cart() {}

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void add(Product product) {
        System.out.println(product);
        System.out.println(data);
        if (data.containsKey(product)){
            int count = data.get(product) + 1;
            data.replace(product, count);
        }else {
            data.put(product, 1);
        }
        sum += product.getDefaultPrice();
        numberOfProducts++;
    }
    
//    public Product find(int id) {
//        //return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
//    }
    
    public void remove(int id) {
        //data.remove(find(id));
    }
//
    public Map<Product, Integer> getAll() {
        return data;
    }
//
//    @Override
//    public List<Product> getBy(Supplier supplier) {
//        return data.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<Product> getBy(ProductCategory productCategory) {
//        return data.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
//    }
//
    public float getSum() {
        return sum;
    }
//
//    @Override
//    public int getNumberOfProducts() {
//        return 0;
//    }
}
