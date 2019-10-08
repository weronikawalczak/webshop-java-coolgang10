package com.codecool.shop.model;

import java.util.Random;

public class Util {
    private static Random random = new Random();

    public static int generateNumber(){
        return random.nextInt(100);
    }
}
