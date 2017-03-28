package com.example.storm.moviesinfo.model.movielist;

/**
 * Created by khb on 2017/3/28.
 */

public class Label1 {
    String name ;
    String link;

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

    @Override
    public String toString() {
        return "Label1{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
