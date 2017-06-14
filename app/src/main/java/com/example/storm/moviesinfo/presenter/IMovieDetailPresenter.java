package com.example.storm.moviesinfo.presenter;

import com.example.storm.moviesinfo.view.activity.MovieDetailActivity;

/**
 * Created by khb on 2017/5/2.
 */

public interface IMovieDetailPresenter extends BasePresenter{
    void register(MovieDetailActivity activity);
    void loadData(String movieName);
}
