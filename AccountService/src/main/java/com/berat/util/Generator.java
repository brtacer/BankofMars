package com.berat.util;

public class Generator {

    public static String randomAccountNumber(){
        Long number = (long) Math.floor(Math.random() * 9_000_000_000L)+1_000_000_000L;
        return number.toString();
    }
    public static String randomIban(){
        Long iban = (long) Math.floor(Math.random() * 9_000_000_000_000L)+1_000_000_000_000L;
        return iban.toString();
    }

}
