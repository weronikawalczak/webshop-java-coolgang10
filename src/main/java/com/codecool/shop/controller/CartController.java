package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Cart;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;

import static java.lang.Integer.parseInt;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {
    Cart cart = Cart.getInstance();
    ProductDaoMem productDaoMem = ProductDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("products", cart.getAll());
        context.setVariable("test", cart.getAll().keySet());
        DecimalFormat df = new DecimalFormat("#.##");
        context.setVariable("sum", df.format(cart.getSum()));
        engine.process("cart/index.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //add
        if(req.getParameter("product_id") != null){
            Integer productId = parseInt(req.getParameter("product_id"));
            cart.add(productDaoMem.find(productId));
            resp.sendRedirect("/");
        }else {//
            //remove
            Integer prod_id = parseInt(req.getParameter("prod_id"));
            cart.remove(prod_id);
            resp.sendRedirect("/cart");
        }
    }
}
