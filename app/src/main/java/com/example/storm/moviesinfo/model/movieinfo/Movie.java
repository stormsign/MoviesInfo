package com.example.storm.moviesinfo.model.movieinfo;

import java.util.List;

/**
 * Created by khb on 2017/3/14.
 */
public class Movie {
    String title;
    String tag;
    String act;
    String year;
    float rating;
    String area;
    String dir;
    String desc;
    String cover;
    String vdo_status;
    PlayLinks playlinks;
    List<VideoRec> video_rec;
    List<Cast> act_s;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getVdo_status() {
        return vdo_status;
    }

    public void setVdo_status(String vdo_status) {
        this.vdo_status = vdo_status;
    }

    public PlayLinks getPlaylinks() {
        return playlinks;
    }

    public void setPlaylinks(PlayLinks playlinks) {
        this.playlinks = playlinks;
    }

    public List<VideoRec> getVideo_rec() {
        return video_rec;
    }

    public void setVideo_rec(List<VideoRec> video_rec) {
        this.video_rec = video_rec;
    }

    public List<Cast> getAct_s() {
        return act_s;
    }

    public void setAct_s(List<Cast> act_s) {
        this.act_s = act_s;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", tag='" + tag + '\'' +
                ", act='" + act + '\'' +
                ", year='" + year + '\'' +
                ", rating=" + rating +
                ", area='" + area + '\'' +
                ", dir='" + dir + '\'' +
                ", desc='" + desc + '\'' +
                ", cover='" + cover + '\'' +
                ", vdo_status='" + vdo_status + '\'' +
                ", playlinks=" + playlinks +
                ", video_rec=" + video_rec +
                ", act_s=" + act_s +
                '}';
    }
}
