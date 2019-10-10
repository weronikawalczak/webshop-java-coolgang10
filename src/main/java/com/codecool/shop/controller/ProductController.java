package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {
    public static ProductController getInstance() {
        return instance;
    }

    private static ProductController instance = new ProductController();
    private String selectedCategory = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());

        renderProductsByCategory(context, productDataStore, productCategoryDataStore, supplierDataStore);

        context.setVariable("selectedCategory", this.selectedCategory);
        engine.process("product/index.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.selectedCategory = req.getParameter("selected_category");
        resp.sendRedirect("");
    }

    private void renderProductsByCategory(WebContext context, ProductDao productDataStore, ProductCategoryDao productCategoryDataStore, SupplierDao supplierDataStore) {
        if(this.selectedCategory.isEmpty()){
            context.setVariable("products", productDataStore.getAll());
        } else if (chechIfStringNum(this.selectedCategory)){
            context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(Integer.parseInt(this.selectedCategory))));
        } else {
           Supplier supplier = supplierDataStore.find(getFirstInt(this.selectedCategory));
           List productDao = productDataStore.getBy(supplier);
           System.out.println(productDao);
           context.setVariable("products", productDao);
        }
    }

    private boolean chechIfStringNum(String text){
        if(text.matches("^[0-9]*$")){
            return true;
        }
        return false;
    }

    private int getFirstInt(String text){
        Matcher matcher = Pattern.compile("\\d+").matcher(text);
        matcher.find();
        int i = Integer.parseInt(matcher.group());
        return i;
    }

    public String sendCategory(String category){
        return category;
    }
}
