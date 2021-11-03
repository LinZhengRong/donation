package com.donation.demo.model;

public class Result<T> {
    private int code;//返回状态码
    private String message;//返回的message
    private T data;//返回的数据

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result(T data) {
        this.code = 0;
        this.message = "success";
        this.data = data;
    }
    public Result(int code,String message,T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public Result() {
        this.code = 0;
        this.message = "success";
        this.data = null;
    }
}
