package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMemJDBC;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.postgresql.ds.PGSimpleDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

@WebListener
public class Initializer implements ServletContextListener {
    public static void main(String[] args) throws SQLException{
//        DataSource dataSource = connect();
//        ProductDaoMem productDaoMem = new ProductDaoMem(dataSource);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            setup();
        } catch (SQLException e) {
            e.printStackTrace();
        }


//        ProductDao productDataStore = ProductDaoMem.getInstance();
//        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
//        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        //setting up Cart
//        Cart cart = Cart.getInstance();
//
//        //setting up a new supplier
//        Supplier amazon = new Supplier("Amazon", "Digital content and services");
//        Supplier lenovo = new Supplier("Lenovo", "Computers");
//        Supplier ebay = new Supplier("Ebay", "International supplier");
//        supplierDataStore.add(amazon);
//        supplierDataStore.add(lenovo);
//        supplierDataStore.add(ebay);
//
//        //setting up a new product category
//        ProductCategory tablet = new ProductCategory("Tablets", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
//        ProductCategory laptop = new ProductCategory("Laptops", "Hardware", "A small, portable personal computer (PC), typically having a thin LCD or LED computer screen mounted on the inside of the upper lid");
//        ProductCategory phone = new ProductCategory("Phones", "Hardware", "Portable telephone that can make and receive calls over a radio frequency");
//        productCategoryDataStore.add(tablet);
//        productCategoryDataStore.add(laptop);
//        productCategoryDataStore.add(phone);
//
//        //setting up products and printing it
//        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
//        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479f, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
//        productDataStore.add(new Product("Amazon Fire HD 8", 89f, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
//        productDataStore.add(new Product("DELL", 89f, "USD", "DELL's latest multipurpose laptop.", laptop, amazon));
//        productDataStore.add(new Product("Asus", 99f, "USD", "Asus's latest laptop.", laptop, amazon));
//        productDataStore.add(new Product("Legion", 189f, "USD", "Gaming laptop.", laptop, lenovo));
//        productDataStore.add(new Product("Iphone", 10000f, "USD", "A phone.", phone, amazon));
//        productDataStore.add(new Product("Samsung", 400f, "USD", "Android phone.", phone, ebay));
//        productDataStore.add(new Product("Xiaomi", 200f, "USD", "Good affordable mobile phone.", phone, ebay));
    }

    private static DataSource connect() throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();

        try (InputStream input = new FileInputStream("src/main/resources/connection.properties")) {

            Properties prop = new Properties();

            prop.load(input);

            dataSource.setDatabaseName(prop.getProperty("db.name"));
            dataSource.setUser(prop.getProperty("db.user"));
            dataSource.setPassword(prop.getProperty("db.password"));
            dataSource.getConnection().close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return dataSource;
    }

    private void setup() throws SQLException{
        DataSource dataSource = connect();
        ProductDaoMemJDBC productDaoMemJDBC = new ProductDaoMemJDBC(dataSource);
    }
}
