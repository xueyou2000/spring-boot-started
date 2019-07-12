package com.xueyou.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 控制器异常捕获
 */
@ResponseBody
@ControllerAdvice
@Slf4j
public class GlobalControllerException {

    @ExceptionHandler(value = Exception.class)
    public Map<String, Object> errorHandle(Exception e) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", -1);
        map.put("msg", e.getMessage());
        log.error("控制器异常捕获: {}",e.getMessage());
        return map;
    }

}
