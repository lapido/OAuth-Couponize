package com.couponize.Auth.controller.model;

import java.util.HashMap;
import java.util.List;

public class Response  {
    private final String code;
    private final String description;
    private final List<java.lang.Error> errors;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public List<java.lang.Error> getErrors() {
        return errors;
    }

    public Response(String code, String description, List<java.lang.Error> errors) {
        this.code = code;
        this.description = description;
        this.errors = errors;
    }
}
