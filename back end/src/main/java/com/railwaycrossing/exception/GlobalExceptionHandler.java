package com.railwaycrossing.exception;


import com.alibaba.fastjson.JSONObject;
import com.railwaycrossing.constants.Constants;
import com.railwaycrossing.utils.JSONUtil;
import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 捕捉Shiro异常
    @ExceptionHandler({ShiroException.class})
    @ResponseBody
    public JSONObject ShiroHandler() {

        return JSONUtil.errorJSON("action failed!");

    }

    // 捕捉插入异常
    @ExceptionHandler({InsertException.class})
    @ResponseBody
    public JSONObject insertException() {

        return JSONUtil.errorJSON(Constants.INSERT_FAILED);
    }

    // 捕捉删除异常
    @ExceptionHandler({DeleteException.class})
    @ResponseBody
    public JSONObject deleteException() {

        return JSONUtil.errorJSON(Constants.DELETE_FAILED);
    }

    // 捕捉更新异常
    @ExceptionHandler({UpdateException.class})
    @ResponseBody
    public JSONObject updateException() {

        return JSONUtil.errorJSON(Constants.UPDATE_FAILED);
    }

    // 捕捉查询异常
    @ExceptionHandler({QueryException.class})
    @ResponseBody
    public JSONObject queryException() {

        return JSONUtil.errorJSON(Constants.QUERY_FAILED);
    }

    // 捕捉其他所有异常
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public JSONObject globalException() {

        return JSONUtil.errorJSON("global error!");
    }

}