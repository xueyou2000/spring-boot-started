package com.xueyou.model.pojo;

import com.xueyou.model.enums.CustomerStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 创建 by xueyo on 2019/7/17
 * Entity 注解表明这是一个JPA实体, 由于缺少 @Table 注解, 所以假定此实体将映射到 Customer 表
 */
@Entity
@Table(name = "customer")
@Data
public class Customer {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 名字
     */
    @Column(name = "FIRST_NAME", length = 30, nullable = false)
    private String firstName;

    /**
     * 姓氏
     */
    @Column(name = "LAST_NAME", length = 30, nullable = false)
    private String lastName;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME", nullable = false)
    private Date createTime;

    /**
     * 年龄
     */
    @Column(name = "AGE", length = 20)
    private int age;

    /**
     * 状态
     */
    @Column(name = "STATUS", length = 15)
    private CustomerStatus status;

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
