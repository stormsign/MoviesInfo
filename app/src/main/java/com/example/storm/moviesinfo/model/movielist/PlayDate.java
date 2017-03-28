package com.example.storm.moviesinfo.model.movielist;

/**
 * Created by khb on 2017/3/28.
 */
public class PlayDate {
    String showname;
    String data;
    String data2;

    public String getShowname() {
        return showname;
    }

    public void setShowname(String showname) {
        this.showname = showname;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    @Override
    public String toString() {
        return "PlayDate{" +
                "showname='" + showname + '\'' +
                ", data='" + data + '\'' +
                ", data2='" + data2 + '\'' +
                '}';
    }
}
