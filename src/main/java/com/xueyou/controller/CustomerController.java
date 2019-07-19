package com.xueyou.controller;

import com.querydsl.core.QueryFactory;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.xueyou.model.pojo.Customer;
import com.xueyou.model.pojo.QCustomer;
import com.xueyou.model.vo.CustomerVo;
import com.xueyou.repository.CustomerRepository;
import com.xueyou.service.CustomerService;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 创建 by xueyo on 2019/7/17
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final JPAQueryFactory jpaQueryFactory;

    public CustomerController(CustomerRepository customerRepository,CustomerService customerService, JPAQueryFactory jpaQueryFactory) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @PostMapping("/add")
    public Customer add(@RequestBody Customer customer) {
        return customerService.add(customer);
    }

    @GetMapping("/findByName")
    public Iterable<Customer> findByName(@RequestParam String firstName, @RequestParam String lastName) {
        return customerRepository.findByLastNameAndFirstName(lastName, firstName);
    }

    @GetMapping("/findFirstByFirstName")
    public Customer findFirstByFirstName(@RequestParam String firstName) {
        return customerRepository.findFirstByFirstName(firstName);
    }

    @PostMapping("/findAll")
    public Iterable<Customer> findAll(@RequestBody Customer customer) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("age", "createTime");
        return customerRepository.findAll(Example.of(customer, matcher));
    }

    @GetMapping("/querydsl-test")
    public Customer test() {
        QCustomer customer = QCustomer.customer;
        return jpaQueryFactory.selectFrom(customer)
                .where(customer.firstName.eq("无量"))
                .orderBy(customer.age.asc())
                .fetchOne();
    }

    @GetMapping("/querydsl-pagequery")
    public List<CustomerVo> pageQuery(@PageableDefault(page = 0, size = 5) Pageable pageable) {
        QCustomer customer = QCustomer.customer;
        QueryResults<Customer> results = jpaQueryFactory.selectFrom(customer)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return results
                .getResults()
                .stream()
                .map(tuple ->
                        CustomerVo.builder()
                            .id(tuple.getId())
                            .firstName(tuple.getFirstName())
                            .lastName(tuple.getLastName())
                            .createTime(tuple.getCreateTime())
//                          .createTime(new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(tuple.getCreateTime()))
                            .build()
                )
                .collect(Collectors.toList());
    }


    @PostMapping("/findAllBySql")
    public Page<Customer> findAllBySql(@RequestParam String lastName, @PageableDefault(page = 0, size = 5, sort = "age", direction = Sort.Direction.DESC) Pageable pageable) {
        return customerRepository.findAllBySql(lastName, pageable);
    }

    @GetMapping("/all")
    public Iterable<Customer> all() {
        return customerRepository.findAll();
    }

}
