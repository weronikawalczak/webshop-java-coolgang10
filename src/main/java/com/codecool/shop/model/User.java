package com.codecool.shop.model;

import java.security.NoSuchAlgorithmException;

public class User {
    String username;
    String email;
    String hashedPass;

    public User(String username, String email, String hashedPass) throws NoSuchAlgorithmException {
        this.username = username;
        this.email = email;
//        this.hashedPass = Util.hashPass(hashedPass);
        this.hashedPass = hashedPass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPass() {
        return hashedPass;
    }

    public void setHashedPass(String hashedPass) {
        this.hashedPass = hashedPass;
    }

}
