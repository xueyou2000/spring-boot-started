package com.xueyou.model.vo;

import com.xueyou.model.enums.ResultCode;
import lombok.Data;

@Data
public class JsonResult<T> {

    private ResultCode code;

    public JsonResult(ResultCode resultCode) {
        code = resultCode;
    }


}
