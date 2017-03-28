package com.example.storm.moviesinfo.model.movielist;

import java.util.List;

/**
 * Created by khb on 2017/3/27.
 */

public class MovieListWrapper {
    String title;
    String url;
    String m_url;
    List<MovieList> data;
    String morelink;
    String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getM_url() {
        return m_url;
    }

    public void setM_url(String m_url) {
        this.m_url = m_url;
    }

    public List<MovieList> getData() {
        return data;
    }

    public void setData(List<MovieList> data) {
        this.data = data;
    }

    public String getMorelink() {
        return morelink;
    }

    public void setMorelink(String morelink) {
        this.morelink = morelink;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MovieListWrapper{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", m_url='" + m_url + '\'' +
                ", data=" + data +
                ", morelink='" + morelink + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
