package com.star.util;

/**
 * @program: myblog
 * @author: cx
 * @create: 2022-02-05 01:32
 * @description: 通用常量信息
 **/

public class Constants
{
    /**
     * UTF-8 字符集
     */
    public static final String UTF8             = "UTF-8";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS          = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL             = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS    = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT           = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL       = "Error";

    /**
     * 自动去除表前缀
     */
    public static final String AUTO_REOMVE_PRE  = "true";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM         = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE        = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN  = "sortField";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC           = "sortOrder";

    public static final String CURRENT_ID       = "current_id";

    public static final String CURRENT_USERNAME = "current_username";

    public static final String TOKEN            = "token";

    public static final String DEFAULT_CODE_KEY = "random_code_";

    public final static String ACCESS_TOKEN     = "access_token_";

    public final static String ACCESS_LEGAL_TOKEN     = "access_legal_token_";

    public final static String ACCESS_USERID    = "access_userid_";

    public static final String RESOURCE_PREFIX  = "/profile";

    /**
     * 请求中的唯一标识
     */
    public static final String REQUEST_ID = "requestId";

    /**
     *
     */
    public  static final String USER_ID = "userId";

    /**
     *
     */
    public  static final String USER_UID = "uid";

    /**
     *
     */
    public  static final String USER_NAME = "userName";
    /**
     *
     */
    public  static final String LOGIN_NAME = "loginName";

    public static final String PASS_ID="exAppId";

    public static final String PASS_TOKEN="exAppToken";

    public static final String REDIS_KEY_LICENSE_FIX = "licenseResponse:";
    public static final String REDIS_KEY_LICENSE_EXAPPID_FIX = "licenseResponseExAppId:";

    /**
     * R对象的状态键值
     */
    public static final String R_CODE="code";


    public static final String INTERFACE_FLAG = "license";

    public static final String EXAPP_PASSTOKEN_INSERT_VERSION = "ExApp_PassToken_Insert_version";
}

