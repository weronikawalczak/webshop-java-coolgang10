package com.codecool.shop.model;

import java.util.HashMap;
import java.util.Map;

public class Cart{
    private static Cart instance = null;
    Map<Product, Integer> data = new HashMap<>();
    private int numberOfProducts;
    private double sum = 0;

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
        numberOfProducts++;
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
        data.remove(find(id));
    }

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
    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
//
//    @Override
//    public int getNumberOfProducts() {
//        return 0;
//    }
}
