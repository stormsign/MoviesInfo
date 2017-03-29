package com.example.storm.moviesinfo.model.movielist;

import com.example.storm.moviesinfo.view.widget.MyRecyclerview.model.Visitor;
import com.example.storm.moviesinfo.view.widget.MyRecyclerview.types.TypeFactory;

/**
 * Created by khb on 2017/3/27.
 */

public class MovieBrief implements Visitor{
    String tvTitle;
    String iconaddress;
    String iconlinkUrl;
    String m_iconlinkUrl;
    String subHead;
    String grade;
    String gradeNum;
    PlayDate playDate;
    LabelType director;
    LabelType star;
    LabelType type;
    Story story;
//    List<LabelGroup> data;


    public String getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(String tvTitle) {
        this.tvTitle = tvTitle;
    }

    public String getIconaddress() {
        return iconaddress;
    }

    public void setIconaddress(String iconaddress) {
        this.iconaddress = iconaddress;
    }

    public String getIconlinkUrl() {
        return iconlinkUrl;
    }

    public void setIconlinkUrl(String iconlinkUrl) {
        this.iconlinkUrl = iconlinkUrl;
    }

    public String getM_iconlinkUrl() {
        return m_iconlinkUrl;
    }

    public void setM_iconlinkUrl(String m_iconlinkUrl) {
        this.m_iconlinkUrl = m_iconlinkUrl;
    }

    public String getSubHead() {
        return subHead;
    }

    public void setSubHead(String subHead) {
        this.subHead = subHead;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(String gradeNum) {
        this.gradeNum = gradeNum;
    }

    public PlayDate getPlayDate() {
        return playDate;
    }

    public void setPlayDate(PlayDate playDate) {
        this.playDate = playDate;
    }

    public LabelType getDirector() {
        return director;
    }

    public void setDirector(LabelType director) {
        this.director = director;
    }

    public LabelType getStar() {
        return star;
    }

    public void setStar(LabelType star) {
        this.star = star;
    }

    public LabelType getType() {
        return type;
    }

    public void setType(LabelType type) {
        this.type = type;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    @Override
    public String toString() {
        return "MovieBrief{" +
                "tvTitle='" + tvTitle + '\'' +
                ", iconaddress='" + iconaddress + '\'' +
                ", iconlinkUrl='" + iconlinkUrl + '\'' +
                ", m_iconlinkUrl='" + m_iconlinkUrl + '\'' +
                ", subHead='" + subHead + '\'' +
                ", grade='" + grade + '\'' +
                ", gradeNum='" + gradeNum + '\'' +
                ", playDate=" + playDate +
                ", director=" + director +
                ", star=" + star +
                ", type=" + type +
                ", story=" + story +
                '}';
    }

    @Override
    public int type(TypeFactory typeFactory) {
        return typeFactory.type(this);
    }
}
