package com.example.storm.moviesinfo.presenter;

import com.example.storm.moviesinfo.view.fragment.MovieListFragment;

/**
 * Created by khb on 2017/3/28.
 */

public interface IMovieListPresenter extends BasePresenter{
    void register(MovieListFragment fragment);
//    void loadData();
    void loadData(int dataType);
    void getLocation();
}
