package com.xueyou.repository;

import com.xueyou.model.pojo.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * 创建 by xueyo on 2019/7/17
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    /**
     * 根据lastName查找商户
     * @param lastName lastName
     * @return 返回商户
     */
    List<Customer> findByLastName(String lastName);

}
