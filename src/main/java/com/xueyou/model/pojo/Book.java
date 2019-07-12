package com.xueyou.model.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建 by xueyo on 2019/7/12
 */
@Data
public class Book implements Serializable {

    private static final long serialVersionUID = 3537921742065870581L;

    /**
     * 编号
     */
    private Long id;

    /**
     * 书名
     */
    private String name;

    /**
     * 作者
     */
    private String writer;

    /**
     * 简介
     */
    private String introduction;

}
