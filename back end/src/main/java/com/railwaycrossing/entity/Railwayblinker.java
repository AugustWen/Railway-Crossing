package com.railwaycrossing.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 铁路信号灯
 */
@Data
public class Railwayblinker implements Serializable {
    private Integer railwayBlinkerId;
    private String railwayBlinkerName;
    private Integer railwayBlinkerStatus;//0允许通行，1禁止，2黄灯
    private Integer crossingId;
}
