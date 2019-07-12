package com.xueyou.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class IndexController {


    @Data
    private class User {
        private String name;
    }

    @GetMapping("/test")
    public User test() {
        User user = new User();
        user.setName("XueYou");
        return user;
    }

    @GetMapping("/exception")
    public User exception() throws Exception {
        throw new Exception("测试异常");
    }

}
