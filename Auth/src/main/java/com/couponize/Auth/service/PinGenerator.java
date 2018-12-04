package com.couponize.Auth.service;


import java.util.UUID;

public class PinGenerator {

    public static String IdGenerator() {
        UUID applicationKey = UUID.randomUUID();
        return applicationKey.toString();
    }

    public static String secretkey() {
        UUID secretKey = UUID.randomUUID();
        return secretKey.toString();
    }

}
