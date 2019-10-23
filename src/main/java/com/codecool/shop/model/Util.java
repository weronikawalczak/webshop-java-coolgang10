package com.codecool.shop.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;


public class Util {
    private static Random random = new Random();

    public static int generateNumber(){
        return random.nextInt(100);
    }


    public static boolean arePasswordsSame(String pass, String repeatedPass){
        if(pass.equals(repeatedPass)){
            return true;
        }
        return false;
    }

//    public static String hashPass(String pass) throws NoSuchAlgorithmException {
////        return BCrypt.hashpw(pass, BCrypt.gensalt());
//
//        SecureRandom random = new SecureRandom();
//        byte[] salt = new byte[16];
//        random.nextBytes(salt);
//
//        MessageDigest md = MessageDigest.getInstance("SHA-512");
//        md.update(salt);
//
//        byte[] hashedPassword = md.digest(pass.getBytes(StandardCharsets.UTF_8));
//
//        return new String(hashedPassword);
//    }

    public static String unhashPass(byte[] pass){
        return "";
    }

}
