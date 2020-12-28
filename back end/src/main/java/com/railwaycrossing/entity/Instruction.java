package com.railwaycrossing.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 指示
 */
@Data
public class Instruction {
    private Integer instructionId;
    private Integer userId;
    private Integer crossingId;
    private String locomotiveId;
    private String instructionContent;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date instructionTime;

    private Integer passTime;
    private Boolean valid;
}
