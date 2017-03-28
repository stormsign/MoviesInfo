package com.example.storm.moviesinfo.model.movielist;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khb on 2017/3/28.
 */

public class LabelGroup {
    @SerializedName("1")
    Label1 link1;
    @SerializedName("m_1")
    Labelm1 linkm1;

    @SerializedName("2")
    Label1 link2;
    @SerializedName("m_2")
    Labelm1 linkm2;

    @SerializedName("3")
    Label1 link3;
    @SerializedName("m_3")
    Labelm1 linkm3;

    @SerializedName("4")
    Label1 link4;
    @SerializedName("m_4")
    Labelm1 linkm4;

    public Label1 getLink1() {
        return link1;
    }

    public void setLink1(Label1 link1) {
        this.link1 = link1;
    }

    public Labelm1 getLinkm1() {
        return linkm1;
    }

    public void setLinkm1(Labelm1 linkm1) {
        this.linkm1 = linkm1;
    }

    public Label1 getLink2() {
        return link2;
    }

    public void setLink2(Label1 link2) {
        this.link2 = link2;
    }

    public Labelm1 getLinkm2() {
        return linkm2;
    }

    public void setLinkm2(Labelm1 linkm2) {
        this.linkm2 = linkm2;
    }

    public Label1 getLink3() {
        return link3;
    }

    public void setLink3(Label1 link3) {
        this.link3 = link3;
    }

    public Labelm1 getLinkm3() {
        return linkm3;
    }

    public void setLinkm3(Labelm1 linkm3) {
        this.linkm3 = linkm3;
    }

    public Label1 getLink4() {
        return link4;
    }

    public void setLink4(Label1 link4) {
        this.link4 = link4;
    }

    public Labelm1 getLinkm4() {
        return linkm4;
    }

    public void setLinkm4(Labelm1 linkm4) {
        this.linkm4 = linkm4;
    }

    @Override
    public String toString() {
        return "LabelGroup{" +
                "link1=" + link1 +
                ", linkm1=" + linkm1 +
                ", link2=" + link2 +
                ", linkm2=" + linkm2 +
                ", link3=" + link3 +
                ", linkm3=" + linkm3 +
                ", link4=" + link4 +
                ", linkm4=" + linkm4 +
                '}';
    }
}
