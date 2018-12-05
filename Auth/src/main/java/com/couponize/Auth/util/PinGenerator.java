package com.couponize.Auth.util;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PinGenerator {

    public String idGenerate() {
        UUID applicationKey = UUID.randomUUID();
        return applicationKey.toString();
    }

    public String keyGenerate() {
        UUID secretKey = UUID.randomUUID();
        return secretKey.toString();
    }

    public Map generate(){
        Map credentials = new HashMap();
        credentials.put("id", idGenerate());
        credentials.put("key", keyGenerate());
        return credentials;
    }

}
