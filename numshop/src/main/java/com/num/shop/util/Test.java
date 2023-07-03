package com.num.shop.util;

import com.num.shop.entity.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Test {
   /* public static void main(String[] args) {
        String password = MD5Utils.getMd5(System.currentTimeMillis() + "");
        System.out.println(password);
        User user = new User();
        user.setBlockpassword(password);
        System.out.println(user.getBlockpassword());

    }*/
    private static Date date = new Date();
    //  private static StringBuilder buf = new StringBuilder();
    private static int seq = 0;
    private static final int ROTATION = 99999;
    public static void main(String [] args){
        Random random = new Random();
        String code = String.format("%5d", random.nextInt(100));
        System.out.println(code);
    }
}
