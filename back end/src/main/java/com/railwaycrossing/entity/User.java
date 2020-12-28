package com.railwaycrossing.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 */
@Data
public class User implements Serializable {
    private Integer userId;
    private String userAccount;
    private String userName;
    private String password;
    private String salt;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;
    private Integer lastLoginLength;
}
