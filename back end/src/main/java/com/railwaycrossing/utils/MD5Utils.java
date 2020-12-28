package com.railwaycrossing.utils;

import org.springframework.util.DigestUtils;

public class MD5Utils {
    /**
     * 将密码执行加密
     * @param password 原密码
     * @param salt 盐值
     * @return 加密后的结果
     */
    public static String getMd5Password(String password, String salt) {
        // 拼接原密码与盐值
        String str = salt + password + salt;
        // 循环加密5次
        for (int i = 0; i < 5; i++) {

        // DigestUtils：springboot提供的工具类
            str = DigestUtils.md5DigestAsHex(
                    str.getBytes()).toUpperCase();
        }
        // 返回结果
        return str;
    }
}
