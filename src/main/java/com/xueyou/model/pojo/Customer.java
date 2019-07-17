package com.xueyou.model.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * 创建 by xueyo on 2019/7/17
 * Entity 注解表明这是一个JPA实体, 由于缺少 @Table 注解, 所以假定此实体将映射到 Customer 表
 */
@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * 默认构造函数仅为JPA而存在。您不会直接使用它，因此它被指定为protected
     */
    protected Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
