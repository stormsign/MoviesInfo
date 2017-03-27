package com.example.storm.moviesinfo.model.movieinfo;

/**
 * Created by khb on 2017/3/14.
 */
public class PlayLinks {
    String levp;
    String youku;
    String imgo;
    String qq;

    public String getLevp() {
        return levp;
    }

    public void setLevp(String levp) {
        this.levp = levp;
    }

    public String getYouku() {
        return youku;
    }

    public void setYouku(String youku) {
        this.youku = youku;
    }

    public String getImgo() {
        return imgo;
    }

    public void setImgo(String imgo) {
        this.imgo = imgo;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Override
    public String toString() {
        return "PlayLinks{" +
                "levp='" + levp + '\'' +
                ", youku='" + youku + '\'' +
                ", imgo='" + imgo + '\'' +
                ", qq='" + qq + '\'' +
                '}';
    }
}
