package com.github.apiaoa.bilibili.framework.api;

/**
 * @Author Sunny
 */
public class Wrapper<T> {
    public static final int ON_SUCCESS = 1;
    public static final int ON_FAILURE = 0;
    public static final int TOKEN_EXPIRE = -1;
    public static final int TOKEN_EXPIRE_REQUEST_CODE = 0X0001;
    private int code;
    private String msg;
    private T data;

    public Wrapper() {
    }

    public Wrapper(int status, String msg, T data) {
        this.code = status;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return code;
    }

    public void setStatus(int status) {
        this.code = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        String string = "{" +
                "\n\t\"status\":" + code +
                "\n\t\"msg\":" + msg +
                "\n\t\"data\":" + (null == data ? null : data.toString()) +
                "\n}";

        return string;
    }

}
