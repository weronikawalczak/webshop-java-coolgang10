package com.codecool.shop.dao.implementation;


import com.codecool.shop.model.User;
import com.codecool.shop.model.UserAddress;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoMemJDBC {
    private static UserDaoMemJDBC instance = null;
    private DataSource dataSource;

    private UserDaoMemJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static UserDaoMemJDBC getInstance(DataSource dataSource) {
        if (instance == null) {
            instance = new UserDaoMemJDBC(dataSource);
        }
        return instance;
    }

    public void add(User user) {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();

            String sql = "INSERT INTO users (username, hashed_pw, email)" +
                    "VALUES ('" + user.getUsername() + "', '" + user.getHashedPass() + "', '" + user.getEmail() + "')";
            stmt.execute(sql);

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
    }

    public boolean find(String username, String pass) {
        return true;
    }

    public String getPass(String username) {
        Connection conn = null;
        Statement stmt = null;
        String pass = "";

        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();

            String sql =    "SELECT hashed_pw FROM users " +
                            "WHERE username ='" +username + "'";

            ResultSet rs = stmt.executeQuery(sql);
//            pass = rs.getString("hashed_pw");

            while (rs.next()) {
                //Retrieve by column name
                pass = rs.getString("hashed_pw");
            }
            rs.close();
            return pass;

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        return null;
    }


//
//    public void remove(int id) {
//        users.remove(find(id));
//    }
//
//    public List<UserAddress> getUsers() {
//        return users;
//    }
}
