package com.xueyou.service;

import com.xueyou.model.enums.CustomerStatus;
import com.xueyou.model.pojo.Customer;
import com.xueyou.repository.CustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 创建 by xueyo on 2019/7/18
 */
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * 新增用户
     * @param customer
     * @return
     */
    public Customer add(Customer customer) {
        customer.setCreateTime(new Date());
        customer.setStatus(CustomerStatus.NORMAL);
        return customerRepository.save(customer);
    }

}
