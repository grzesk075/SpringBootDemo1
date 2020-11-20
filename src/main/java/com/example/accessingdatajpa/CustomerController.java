package com.example.accessingdatajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/getall")
    public Iterable<Customer> allCustomers() {
        return customerRepository.findAll();
    }

    @PostMapping("/add")
    public String add(@RequestParam String firstName, @RequestParam String lastName) {
        customerRepository.save(new Customer(firstName, lastName));
        return "Saved";
    }
    //curl localhost:8080/customer/add -d firstName=Greg -d lastName=Kupriano
}
