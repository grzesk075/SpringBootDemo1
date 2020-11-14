package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestController1 {

    @GetMapping("/hello1")
    public String hello(@RequestParam(name = "name", defaultValue = "World") String name) {
        return String.format("Hello one %s!", name);
    }

}
