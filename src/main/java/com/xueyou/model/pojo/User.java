package com.xueyou.model.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 创建 by xueyo on 2019/7/12
 */
@Data
@Entity
@Table(name = "user")
public class User {

    /**
     * 用户id
     */
    @Column(name = "USER_ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**
     * 用户名称
     */
    @Column(name = "USERNAME", length = 30, nullable = true)
    private String username;

    /**
     * 密码
     */
    @Column(name = "PASSWORD", length = 30, nullable = true)
    private String password;

    /**
     * 昵称
     */
    @Column(name = "NICKNAME", length = 30, nullable = true)
    private String nickname;

    /**
     * 生日
     */
    @Column(name = "BIRTHDAY")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date birthday;

    /**
     * 排序号
     */
    @Column(name = "U_INDEX")
    private BigDecimal uIndex;

    /**
     * 部门实体: 一对多映射
     */
    // cascade 设置级联类型为合并类型
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    // 设置此列字段为关联列
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

}
