package com.xueyou.repository;

import com.xueyou.model.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 创建 by xueyo on 2019/7/17
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /**
     * 根据lastName查找商户
     * @param lastName lastName
     * @return 返回商户
     */
    List<Customer> findByLastName(String lastName);

    /**
     * 根据全名查询商户
     * @param lastName 姓
     * @param firstName 名
     * @return 返回商户集合
     */
    List<Customer> findByLastNameAndFirstName(String lastName, String firstName);

    Customer findFirstByFirstName(String firstName);


}
