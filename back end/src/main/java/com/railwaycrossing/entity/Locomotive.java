package com.railwaycrossing.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 机车
 */
@Data
public class Locomotive {
    private Integer locomotiveId;
    private String locomotiveName;
    private String locomotiveGPS;
    private Integer nextCrossingId;
    private String trackId;
    private Boolean responseStatus;
}
