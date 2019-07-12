package com.xueyou.model.vo;

import com.xueyou.model.enums.ResultCode;
import lombok.Data;

@Data
public class JsonResult<T> {

    /**
     * 错误类型
     */
    private ResultCode code;

    /**
     * 错误原因
     */
    private String message;

    public JsonResult(ResultCode resultCode) {
        code = resultCode;
    }

    public JsonResult(ResultCode resultCode, String message) {
        code = resultCode;
        this.message = message;
    }

}
