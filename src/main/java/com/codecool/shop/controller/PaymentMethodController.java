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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("cart/confirmation.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        String paymentMethod = req.getParameter("paymentMethod");
        if (paymentMethod.equals("payPal")) {
            engine.process("cart/paypal.html", context, resp.getWriter());
        }else{
            engine.process("cart/creditCard.html", context, resp.getWriter());
        }
    }
    //ZROB TU Z PAWLEM POZNIEJ ERRROR HANDLING!!!!!!!!!!!!!!!!!!!!!!!!!!
}