package com.couponize.Auth.model;

import com.couponize.Auth.controller.model.Response;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;

public class SignupResponse extends Response {

    private PasswordEncoder userPasswordEncoder;
    private PasswordEncoder clientId;

    public SignupResponse(String code, String description, List<Error> errors) {
        super(code, description, errors);

    }


}
