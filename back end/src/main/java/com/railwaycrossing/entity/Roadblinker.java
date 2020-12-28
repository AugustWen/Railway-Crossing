package com.railwaycrossing.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 铁路信号灯
 */
@Data
public class Roadblinker implements Serializable {
    private Integer roadBlinkerId;
    private String roadBlinkerName;
    private String roadBlinkerStatus;//0允许通行，1禁止，2黄灯
    private Integer crossingId;
}
