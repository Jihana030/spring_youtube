package com.example.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//controller가 우선순위여서 먼저 찾는다. index가 있어도 밀림.
@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
