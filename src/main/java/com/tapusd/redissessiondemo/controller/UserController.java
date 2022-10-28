package com.tapusd.redissessiondemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping({"", "/"})
    public String userHome() {
        System.out.println("user hit");
        return "Welcome user!";
    }

}
