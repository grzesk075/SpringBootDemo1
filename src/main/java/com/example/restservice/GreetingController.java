package com.example.restservice;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicLong;

/**
 * RESTful web service controller.
 */
@RestController
public class GreetingController {

    private static final String TEMPLATE = "Hello %s!";

    private final AtomicLong counter = new AtomicLong();

    @Resource(name = "requestScopedBean")
    private Greeting requestScopedGreeting;

    @GetMapping(value = "/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, path = "/greeting1")
    public Greeting greeting1(@RequestParam(value = "name", defaultValue = "Me") final String name) {
        return requestScopedGreeting; // misfortune example in Rest Controller - spring proxy is passed
    }
}
