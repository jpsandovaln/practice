package com.jalasoft.practice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @PostMapping
    public String sayHello(@RequestParam(value="name") String name,
                           @RequestParam(value="lastName") String lname) {
        return "Hello " + name + " " + lname;
    }
}
