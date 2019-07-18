package com.xueyou.controller;

import com.xueyou.model.pojo.Customer;
import com.xueyou.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

/**
 * 创建 by xueyo on 2019/7/17
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/add")
    public Customer add(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @GetMapping("/findByName")
    public Iterable<Customer> findByName(@RequestParam String firstName, @RequestParam String lastName) {
        return customerRepository.findByLastNameAndFirstName(lastName, firstName);
    }

    @GetMapping("/findFirstByFirstName")
    public Customer findFirstByFirstName(@RequestParam String firstName) {
        return customerRepository.findFirstByFirstName(firstName);
    }

    @GetMapping("/all")
    public Iterable<Customer> all() {
        return customerRepository.findAll();
    }

}
