package com.example.storm.moviesinfo.net;

/**
 * Created by khb on 2017/3/30.
 */

public class ResultException extends RuntimeException{
    public static int INVALID_DATA = -1;
    public static int EMPTY = 0;
    int code;
    String msg;

    public ResultException(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
