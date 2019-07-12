package com.xueyou.controller;

import com.xueyou.model.pojo.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
@Slf4j
public class IndexController {

    /**
     * 自定义日期绑定
     */
    private class CustomDateEditor extends PropertyEditorSupport {
        /**
         * Sets the property value by parsing a given String.  May raise
         * java.lang.IllegalArgumentException if either the String is
         * badly formatted or if this kind of property can't be expressed
         * as text.
         *
         * @param text The string to be parsed.
         */
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            //转换yyyy-MM-dd格式数据
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            //如果不为空就格式化
            if(!StringUtils.isEmpty(text)){
                try {
                    date = format.parse(text);
                } catch (ParseException e) {
                    //转换为yyyy-MM-dd格式数据
                    format = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        date = format.parse(text);
                    } catch (ParseException e1) {
                        log.error(e1.getMessage());
                    }
                }
            }
            setValue(date);
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //        org.springframework.beans.propertyeditors.CustomDateEditor editor = new org.springframework.beans.propertyeditors.CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
        //        binder.registerCustomEditor(Date.class, editor);

        // 与上面类似, 但是自己写 PropertyEditor
        binder.registerCustomEditor(Date.class, new CustomDateEditor());
    }

    /**
     * 测试新增用户转换 时间字符串 为 Date
     * { "name": "XueYou", "date": "2019-5-6" }
     * @param user
     */
    @PostMapping("/add")
    public void addUser(@RequestBody User user) {
        log.info(user.getDate().toString());
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
