package com.couponize.client;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ClientReponse {

    @RequestMapping("/getName")
    public String getName(){
        return "Hello World";
    }
}
