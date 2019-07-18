package com.hb.ssm.sys.model;

/**
 * @description: 公共类
 * @author: huangbo
 * @create: 2019-07-17 13:55
 **/

public class Result {
    private String message;
    private boolean success;
    private Object object;

    private static Result result = new Result();

    public static Result getInstallResult (){
        return result;
    }

    public Result() {
    }

    public Result(Object object) {
        this.object = object;
        this.success = true;
    }

    public Result(String message, boolean success) {
        message = message;
        this.success = success;
    }

    public Result(boolean success, Object object) {
        this.success = success;
        this.object = object;
    }

    public Result(String message, boolean success, Object object) {
        message = message;
        this.success = success;
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}