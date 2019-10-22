package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoMemJDBC implements ProductDao {
    private static DataSource dataSource;
    Product product;

    private List<Product> data = new ArrayList<>();
    private static ProductDaoMemJDBC instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    public ProductDaoMemJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

//    public ProductDaoMem() {
//    }

    public static ProductDaoMemJDBC getInstance() {
        if (instance == null) {
            instance = new ProductDaoMemJDBC(dataSource);
        }
        return instance;
    }

    @Override
    public void add(Product product) {
//        product.setId(data.size() + 1);
//        data.add(product);

//        Connection conn = null;
//        Statement stmt = null;
//        try {
//            conn = dataSource.getConnection();
//            stmt = conn.createStatement();
//
//            String sql =    "INSERT INTO products (name, category_id, supplier_id, price)" +
//                            "VALUES ('" + product.getName() + "', '" + product.getProductCategory().getId() + "', '" +  product.getSupplier().getId() + "', '" +product.getPrice() +"')";
//            stmt.execute(sql);
//
//        }catch(SQLException se) {
//            //Handle errors for JDBC
//            se.printStackTrace();
//        }
    }

    @Override
    public Product find(int id) {
        return data.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        data.remove(find(id));
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;

        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();

            String sql = "SELECT * FROM products ";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                //Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int categoryId = rs.getInt("category_id");
                int supplierId = rs.getInt("supplier_id");
                String price = rs.getString("price");
                this.product = new Product(id, name, categoryId, supplierId, price);
                products.add(product);
            }
            rs.close();

        }catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return data.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return data.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }
}
