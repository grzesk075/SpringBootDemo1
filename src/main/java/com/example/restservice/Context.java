package com.example.restservice;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class Context {

    private static final AtomicLong counter = new AtomicLong(0L);

    @Bean
    @RequestScope
    public Greeting requestScopedBean(){
        return new Greeting(counter.getAndIncrement());
    }
}
