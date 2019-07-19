package com.xueyou.controller;

import com.xueyou.model.vo.UserDeptVo;
import com.xueyou.model.vo.UserInDeptVo;
import com.xueyou.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 创建 by xueyo on 2019/7/19
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/findByDepartmentId")
    public List<UserDeptVo> findByDepartmentId(@RequestParam Integer departmentId) {
        return userService.findByDepartmentId(departmentId);
    }

    @GetMapping("/findInDeptByDepartmentId")
    public List<UserInDeptVo> findInDeptByDepartmentId(@RequestParam Integer departmentId) {
        return userService.findInDeptByDepartmentId(departmentId);
    }

}
