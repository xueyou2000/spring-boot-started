package com.xueyou.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 创建 by xueyo on 2019/7/19
 */
@Data
@Builder
public class CustomerVo {

    private Long id;
    private String firstName;
    private String lastName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

}
