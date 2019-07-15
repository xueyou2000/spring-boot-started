package com.xueyou.model.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 创建 by xueyo on 2019/7/12
 * DROP TABLE IF EXISTS `book`;
 * CREATE TABLE `book` (
 *   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
 *   `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
 *   `name` varchar(50) DEFAULT NULL COMMENT '名称',
 *   `writer` varchar(30) DEFAULT NULL COMMENT '作者',
 *   `introduction` varchar(100) DEFAULT NULL COMMENT '简介',
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='书籍';
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

    /**
     * 创建时间
     */
    private Date createTime;

}
