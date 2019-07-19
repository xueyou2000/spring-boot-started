package com.xueyou.model.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 创建 by xueyo on 2019/7/19
 */
@Entity
@Data
@Table(name = "department")
public class Department {

    /**
     * 部门Id
     */
    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 部门名称
     */
    @Column(name = "DEPT_NAME", length = 30, nullable = true)
    private String deptName;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_DATE", nullable = true)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDate;

}
