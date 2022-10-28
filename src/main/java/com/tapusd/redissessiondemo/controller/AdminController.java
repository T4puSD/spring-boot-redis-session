package com.tapusd.redissessiondemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping({"", "/"})
    public String adminHome() {
        System.out.println("admin hit");
        return "Welcome admin!";
    }

}
