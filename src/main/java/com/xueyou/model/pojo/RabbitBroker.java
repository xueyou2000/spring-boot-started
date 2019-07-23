package com.xueyou.model.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.fasterxml.jackson.databind.ser.std.DateTimeSerializerBase;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 创建 by xueyo on 2019/7/23
 */
@Data
public class RabbitBroker {

    private Integer age;

    private String name;

    private Double amount;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createDate;

    @JsonSerialize
    @JsonDeserialize
    private Date updateDate;

    @Override
    public String toString() {
        return "RabbitBroker{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}
