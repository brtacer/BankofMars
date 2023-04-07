package com.berat.util;

public class Generator {
    public static String randomCVV() {
        Long number = (int) Math.floor(Math.random() * 9_00L) + 1_00L;
        String accountNumber =number.toString();
        return accountNumber;
    }
}
