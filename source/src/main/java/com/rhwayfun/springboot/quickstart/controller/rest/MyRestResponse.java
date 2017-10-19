package com.rhwayfun.springboot.quickstart.controller.rest;

/**
 * Created by chubin on 2017/2/12.
 */
public class MyRestResponse {
    private String message;

    public MyRestResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
