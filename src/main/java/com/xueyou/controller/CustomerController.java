package com.xueyou.controller;

import com.xueyou.model.pojo.Customer;
import com.xueyou.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 创建 by xueyo on 2019/7/17
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/add")
    public Customer add(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping("/all")
    public Iterable<Customer> all() {
        return customerRepository.findAll();
    }

}
