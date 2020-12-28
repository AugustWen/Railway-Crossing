package com.railwaycrossing.constants;

public class Constants {

    public static final Integer SUCCESS_CODE = 200;

    public static final Integer FAILED_CODE = 500;

    public static final Integer NOTFOUND_CODE = 404;

    public static final Integer EROOR_CODE = 400;

    public static final Integer OPEN = 0;//开启

    public static final Integer CLOSE = 1;//关闭

    public static final Integer CHANGING = 2;//上升、下降中

    public static final Integer DOWN = 3;//坏了

    /** 超级管理员ID */
    public static final int SUPER_ADMIN = 1;
    /**
     * 当前页码
     */
    public static final String PAGE = "page";
    /**
     * 每页显示记录数
     */
    public static final String LIMIT = "limit";
    /**
     * 排序字段
     */
    public static final String ORDER_FIELD = "sidx";
    /**
     * 排序方式
     */
    public static final String ORDER = "order";
    /**
     *  升序
     */
    public static final String ASC = "asc";

    public static final String QUERY_SUCCESS = "query successful";

    public static final String INSERT_SUCCESS = "insert successful";

    public static final String DELETE_SUCCESS = "delete successful";

    public static final String UPDATE_SUCCESS = "update successful";

    public static final String QUERY_FAILED = "query failed";

    public static final String INSERT_FAILED = "insert failed";

    public static final String DELETE_FAILED = "delete failed";

    public static final String UPDATE_FAILED = "update failed";
}
