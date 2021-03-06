package com.example.storm.moviesinfo.model.movielist;

import java.util.List;

/**
 * Created by khb on 2017/3/27.
 */

public class MovieList {
    String name;
    String link;
    List<MovieBrief> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<MovieBrief> getData() {
        return data;
    }

    public void setData(List<MovieBrief> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MovieList{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", data=" + data +
                '}';
    }
}
