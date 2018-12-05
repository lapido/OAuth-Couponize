package com.couponize.Auth.util;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PinGeneratorTest {

    @Test
    public void testPin(){
        PinGenerator gen = new PinGenerator();
        System.out.println(gen.generate());
    }
}
