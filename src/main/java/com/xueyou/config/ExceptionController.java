package com.xueyou.config;

import com.xueyou.model.enums.ResultCode;
import com.xueyou.model.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 定义全局异常捕获
 */
@Controller
@RequestMapping({"${server.error.path:${error.path:/error}}"})
@Slf4j
public class ExceptionController extends AbstractErrorController {

    private final ErrorProperties errorProperties;

    public ExceptionController(ErrorAttributes errorAttributes, ErrorProperties errorProperties) {
        this(errorAttributes, errorProperties, Collections.emptyList());
    }

    public ExceptionController(ErrorAttributes errorAttributes, ErrorProperties errorProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorViewResolvers);
        Assert.notNull(errorProperties, "ErrorProperties must not be null");
        this.errorProperties = errorProperties;
    }

    /**
     * 页面异常
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = getStatus(request);
        log.error("接口请求错误，url: {} status：{}", request.getRequestURI(), status);
        Map<String, Object> model = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        JsonResult<Object> result = new JsonResult<>(ResultCode.NOT_FOUND);
        model.put("error", result);
        response.setStatus(status.value());
        ModelAndView modelAndView = resolveErrorView(request, response, status, model);
        return (modelAndView == null ? new ModelAndView("error", model) : modelAndView);
    }

    /**
     * 接口异常
     * @param request
     * @return
     */
    @RequestMapping
    @ResponseBody
    public ResponseEntity<JsonResult<String>> error(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        log.error("接口请求错误，url: {} status：{}", request.getRequestURI(), status);
        //这里构建自己的输出格式,详细代码就不贴出,如有需要可以到代码仓库查看
        if (Objects.equals(status.value(), 404)) {
            return new ResponseEntity<>(new JsonResult<>(ResultCode.NOT_FOUND), status);
        } else {
            return new ResponseEntity<>(new JsonResult<>(ResultCode.ERROR_SYSTEM), status);
        }
    }


    /**
     * Returns the path of the error page.
     *
     * @return the error path
     */
    @Override
    public String getErrorPath() {
        return this.errorProperties.getPath();
    }

    protected boolean isIncludeStackTrace(HttpServletRequest request, MediaType produces) {
        ErrorProperties.IncludeStacktrace include = this.getErrorProperties().getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        } else {
            return include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM ? this.getTraceParameter(request) : false;
        }
    }

    protected ErrorProperties getErrorProperties() {
        return this.errorProperties;
    }

}
