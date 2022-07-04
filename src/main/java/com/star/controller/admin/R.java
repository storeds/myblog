package com.star.controller.admin;

import com.star.util.Constants;
import com.star.util.ServletUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: myblog
 * @author: cx
 * @create: 2022-02-05 01:16
 * @description:
 **/
public class R extends HashMap<String, Object>  {
    private static final long serialVersionUID = -8157613083634272196L;

    public static final int SUCCESS = 0;
    public static final int ERROR = 500;

    public static final String OK = "0";
    public static final String FAIL = "500";

    public R() {
        put("code" , SUCCESS);
        put("msg" , "success");
        //将日志链路ID返回给调用方，方便排查日志信息
        String requestId = "";
        try {
            requestId =  ServletUtils.getRequest().getHeader(Constants.REQUEST_ID);
        }catch (Exception e){}
        put(Constants.REQUEST_ID, requestId);
    }

    public int getCode(){
        return Integer.parseInt(String.valueOf(super.get("code")));
    }

    public static R error() {
        return error(ERROR, "未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(ERROR, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code" , code);
        r.put("msg" , msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg" , msg);
        return r;
    }

    public static R data(Object obj) {
        R r = new R();
        r.put("data" , obj);
        return r;
    }
    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
