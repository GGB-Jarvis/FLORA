package com.num.shop;

import java.util.Random;

public class demo {
    public static void main(String[] args) {
        Random random = new Random();
        String tCode = random.nextInt(1000000)+"";
        if(tCode.length()<6){
            int c = 6 - tCode.length();
            for (int i = 0; i < c; i++) {
                tCode = "0" + tCode;
            }
        }
        System.out.println(tCode);
    }
}
