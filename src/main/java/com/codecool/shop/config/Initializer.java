package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up Cart
        Cart cart = Cart.getInstance();

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        Supplier ebay = new Supplier("Ebay", "International supplier");
        supplierDataStore.add(amazon);
        supplierDataStore.add(lenovo);
        supplierDataStore.add(ebay);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablets", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
        ProductCategory laptop = new ProductCategory("Laptops", "Hardware", "A small, portable personal computer (PC), typically having a thin LCD or LED computer screen mounted on the inside of the upper lid");
        ProductCategory phone = new ProductCategory("Phones", "Hardware", "Portable telephone that can make and receive calls over a radio frequency");
        productCategoryDataStore.add(tablet);
        productCategoryDataStore.add(laptop);
        productCategoryDataStore.add(phone);

        //setting up products and printing it
        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
        productDataStore.add(new Product("DELL", 89, "USD", "DELL's latest multipurpose laptop.", laptop, amazon));
        productDataStore.add(new Product("Asus", 99, "USD", "Asus's latest laptop.", laptop, amazon));
        productDataStore.add(new Product("Legion", 189, "USD", "Gaming laptop.", laptop, lenovo));
        productDataStore.add(new Product("Iphone", 10000, "USD", "A phone.", phone, amazon));
        productDataStore.add(new Product("Samsung", 400, "USD", "Android phone.", phone, ebay));
        productDataStore.add(new Product("Xiaomi", 200, "USD", "Good affordable mobile phone.", phone, ebay));
    }
}
