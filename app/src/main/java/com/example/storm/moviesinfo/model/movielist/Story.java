package com.example.storm.moviesinfo.model.movielist;

/**
 * Created by khb on 2017/3/28.
 */

public class Story {
    String showname;
    StoryBrief data;

    public String getShowname() {
        return showname;
    }

    public void setShowname(String showname) {
        this.showname = showname;
    }

    public StoryBrief getData() {
        return data;
    }

    public void setData(StoryBrief data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Story{" +
                "showname='" + showname + '\'' +
                ", data=" + data +
                '}';
    }
}
