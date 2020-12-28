package com.railwaycrossing.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


/**
 *栏杆
 */
@Data
public class Baluster {
    private Integer balusterId;
    private String balusterName;

    private Integer balusterStatus; //0开，1关，2正在开关，3停止
    private Integer crossingId;
}
