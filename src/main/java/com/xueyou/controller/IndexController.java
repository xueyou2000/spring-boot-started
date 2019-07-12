package com.xueyou.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


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

    /**
     * 测试控制器异常拦截
     * @return
     * @throws Exception
     */
    @GetMapping("/exception")
    public User exception() throws Exception {
        throw new Exception("测试异常");
    }

    /**
     * 测试自定义转换器
     * @param name
     * @return
     */
    @RequestMapping(value = "/convert", method = RequestMethod.POST)
    public String convert(@RequestBody String name) {
        return name;
    }

}
