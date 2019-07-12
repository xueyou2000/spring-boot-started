package com.xueyou.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 配置全局异常处理器
 */
@Configuration
@AutoConfigureBefore
public class ExceptionControllerConfig {

    private final ServerProperties serverProperties;

    private final List<ErrorViewResolver> errorViewResolverList;

    public ExceptionControllerConfig(ServerProperties serverProperties, ObjectProvider<List<ErrorViewResolver>> errorViewResolversProvider) {
        this.serverProperties = serverProperties;
        this.errorViewResolverList = errorViewResolversProvider.getIfAvailable();
    }

    @Bean
    public ExceptionController exceptionController(ErrorAttributes errorAttributes) {
        return new ExceptionController(errorAttributes, this.serverProperties.getError(), this.errorViewResolverList);
    }


}
