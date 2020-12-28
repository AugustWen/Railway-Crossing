package com.railwaycrossing.entity;

import lombok.Data;

/**
 * 警报
 */
@Data
public class Warning {
    private Integer warningId;
    private String warningContent;
    private Integer crossingId;
    private Boolean valid;
}
