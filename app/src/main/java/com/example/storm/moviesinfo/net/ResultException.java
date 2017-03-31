package com.example.storm.moviesinfo.net;

/**
 * Created by 10097 on 2017/3/30.
 */

public class ResultException extends RuntimeException {

    private int code;
    private String msg;

    public ResultException(int code, String msg){

    }

}
