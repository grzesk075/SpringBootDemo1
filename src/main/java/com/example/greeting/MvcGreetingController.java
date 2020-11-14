package com.example.greeting;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MvcGreetingController {

    @GetMapping("/mvcgreeting")
    public String mvcgreeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
                           Model model) {
        model.addAttribute("name", name.toUpperCase());
        return "/mvcgreeting";
    }
}
