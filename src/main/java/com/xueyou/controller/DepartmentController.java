package com.xueyou.controller;

import com.xueyou.model.dto.DepartmentCreateWithUsersDto;
import com.xueyou.model.pojo.Department;
import com.xueyou.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 创建 by xueyo on 2019/7/19
 */
@RestController
@RequestMapping("/department")
@Slf4j
public class DepartmentController {

    private final DepartmentService departmentService;

    /**
     * 构造函数
     * @param departmentService 部门服务
     */
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * 创建部门
     * @param deptName 部门名称
     * @return 刚创建的部门
     */
    @GetMapping("/createByName")
    public Department createByName(@RequestParam String deptName) {
        return departmentService.createByName(deptName);
    }

    /**
     * 创建部门及相关用户
     * @param departmentCreateWithUsersDto 部门创建dto
     * @return 刚创建的部门
     */
    @PostMapping("/createByNameWithUsers")
    public Department createByNameWithUsers(@RequestBody DepartmentCreateWithUsersDto departmentCreateWithUsersDto) {
        return departmentService.createByNameWithUsers(departmentCreateWithUsersDto.getDeptName(), departmentCreateWithUsersDto.getUserList());
    }

}
