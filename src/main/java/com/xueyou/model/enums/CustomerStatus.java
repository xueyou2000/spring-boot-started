package com.xueyou.model.enums;

/**
 * 创建 by xueyo on 2019/7/18
 */
public enum CustomerStatus {
    NORMAL("正常"),
    DISABLED("禁用");

    /**
     * 描述
     */
    private String desc;


    public String getDesc() {
        return desc;
    }

    CustomerStatus(String desc) {
        this.desc = desc;
    }
}
