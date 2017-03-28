package com.example.storm.moviesinfo.model.movielist;

/**
 * Created by khb on 2017/3/28.
 */

public class StoryBrief {
    String storyBrief;
    String storyMoreLink;

    public String getStoryBrief() {
        return storyBrief;
    }

    public void setStoryBrief(String storyBrief) {
        this.storyBrief = storyBrief;
    }

    public String getStoryMoreLink() {
        return storyMoreLink;
    }

    public void setStoryMoreLink(String storyMoreLink) {
        this.storyMoreLink = storyMoreLink;
    }

    @Override
    public String toString() {
        return "StoryBrief{" +
                "storyBrief='" + storyBrief + '\'' +
                ", storyMoreLink='" + storyMoreLink + '\'' +
                '}';
    }
}
