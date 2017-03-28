package com.example.storm.moviesinfo.model.movielist;

/**
 * Created by khb on 2017/3/28.
 */

public class LabelType {
    String showname;
    LabelGroup data;

    public String getShowname() {
        return showname;
    }

    public void setShowname(String showname) {
        this.showname = showname;
    }

    public LabelGroup getData() {
        return data;
    }

    public void setData(LabelGroup data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LabelType{" +
                "showname='" + showname + '\'' +
                ", data=" + data +
                '}';
    }
}
