package com.xueyou.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xueyou.model.pojo.Department;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 创建 by xueyo on 2019/7/19
 */
@Data
@Builder
public class UserInDeptVo {

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 生日
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date birthday;

    /**
     * 所属部门
     */
    private Department department;

}
