package com.xueyou.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 创建 by xueyo on 2019/7/10
 * 自定义转换器, 用于转换 ResponseBody 进入控制器参数和返回给用户的数据
 */
@Slf4j
public class MyConverter extends AbstractHttpMessageConverter<String> {

    MyConverter() {
        super(MediaType.ALL);
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        // 只处理 TestParam 类型的参数
        return String.class.isAssignableFrom(aClass);
    }

    @Override
    protected String readInternal(Class<? extends String> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(httpInputMessage.getBody(), Charset.forName("UTF-8"));
        log.info("传入的参数 {}", temp);
        return temp;
    }

    @Override
    protected void writeInternal(String string, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        String out = "{ \"my-name\": \"" + string + "\" }";
        httpOutputMessage.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        httpOutputMessage.getBody().write(out.getBytes());
    }


}
