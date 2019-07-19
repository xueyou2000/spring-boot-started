package com.xueyou.model.dto;

import com.xueyou.model.pojo.User;
import lombok.Data;

import java.util.List;

/**
 * 创建 by xueyo on 2019/7/19
 */
@Data
public class DepartmentCreateWithUsersDto {

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 用户列表
     */
    private List<User> userList;

}
