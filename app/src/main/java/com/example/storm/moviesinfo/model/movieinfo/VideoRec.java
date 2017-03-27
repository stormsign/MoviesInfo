package com.example.storm.moviesinfo.model.movieinfo;

/**
 * Created by khb on 2017/3/14.
 */
public class VideoRec {
    String cover;
    String detail_url;
    String title;

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDetail_url() {
        return detail_url;
    }

    public void setDetail_url(String detail_url) {
        this.detail_url = detail_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "VideoRec{" +
                "cover='" + cover + '\'' +
                ", detail_url='" + detail_url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
