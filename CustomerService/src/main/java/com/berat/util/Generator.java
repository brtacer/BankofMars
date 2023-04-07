package com.berat.util;

public class Generator {
    public static String randomCustomerNumber() {
        Long number = (long) Math.floor(Math.random() * 90_000_000L) + 10_000_000L;
        String accountNumber =number.toString();
        return accountNumber;
    }
}
