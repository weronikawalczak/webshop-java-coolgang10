package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.Util;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/confirmation"})
public class ConfirmationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int probabilityOfWrongPayment = 30;

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        if(Util.generateNumber() < probabilityOfWrongPayment){
            context.setVariable("result", "Payment confirmed!");
            context.setVariable("orderInfo", "details of the Order");
            // w tym momecie sie zapisuje order i user?
        }else {
            context.setVariable("result", "Some problem with payment!");
            context.setVariable("errorInfo", "the details of the error");
        }
        engine.process("cart/confirmation.html", context, resp.getWriter());
    }
}