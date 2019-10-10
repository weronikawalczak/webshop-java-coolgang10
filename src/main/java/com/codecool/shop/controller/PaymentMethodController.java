package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet(urlPatterns = {"/paymentMethod"})
public class PaymentMethodController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //creditCard
//        if(req.getParameter("product_id") != null){
//            Integer productId = parseInt(req.getParameter("product_id"));
//            cart.add(productDaoMem.find(productId));
//            resp.sendRedirect("/");
//        }else {
//        //paypal
//            Integer prod_id = parseInt(req.getParameter("prod_id"));
//            cart.remove(prod_id);
//            cart.setSum(0);
//            resp.sendRedirect("/cart");
//        }
        resp.getWriter().write( "User chose: " + req.getAttribute( "isTitles" ) );
    }
}