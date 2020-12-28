package com.railwaycrossing.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 道口
 */
@Data
public class Crossing {
    private Integer crossingId;
    private String crossingName;
    private String crossingGPS;
    private Boolean crossingMode;
}
