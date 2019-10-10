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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
//        context.setVariable("category", productCategoryDataStore.find(1));
        context.setVariable("categories", productCategoryDataStore.getAll());
//        context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        context.setVariable("products", productDataStore.getAll());
        context.setVariable("suppliers", supplierDataStore.getAll());
        engine.process("product/index.html", context, resp.getWriter());


    }

    // // Alternative setting of the template context
    // Map<String, Object> params = new HashMap<>();
    // params.put("category", productCategoryDataStore.find(1));
    // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
    // context.setVariables(params);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String selectedCategory = req.getParameter("selection");
//        System.out.println(selectedCategory.matches(".*\\d.*"));
        System.out.println(getFirstInt(selectedCategory));

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
//        context.setVariable("category", productCategoryDataStore.find(1));
        context.setVariable("suppliers", supplierDataStore.getAll());

        context.setVariable("categories", productCategoryDataStore.getAll());

        if(selectedCategory.equals("0")){
            context.setVariable("products", productDataStore.getAll());
        }
        else if (chechIfStringNum(selectedCategory)){
            context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(Integer.parseInt(selectedCategory))));
        }
        else{
           Supplier supplier = supplierDataStore.find(getFirstInt(selectedCategory));
            List productDao = productDataStore.getBy(supplier);
            System.out.println(productDao);
            context.setVariable("products", productDao);
    }

        engine.process("product/index.html", context, resp.getWriter());


//        response.sendRedirect("/shop");
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
