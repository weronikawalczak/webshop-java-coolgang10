package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.dao.implementation.UserDaoMemJDBC;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.User;
import com.codecool.shop.model.UserAddress;
import com.codecool.shop.model.Util;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(urlPatterns = {"/registration"})
public class RegistrationController extends HttpServlet {
    private DataSource dataSource;
//    private Order order;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
                engine.process("registration.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        req.getParameter("pass");
        req.getParameter("repeated_password");

        if(Util.arePasswordsSame(req.getParameter("pass"),req.getParameter("repeated_password"))){
            User user = null;
            try {
                user = new User(
                        req.getParameter("username"),
                        req.getParameter("email"),
                        req.getParameter("pass"));
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            UserDaoMemJDBC.getInstance(dataSource).add(user);
            resp.sendRedirect("/");
        }
    }
}