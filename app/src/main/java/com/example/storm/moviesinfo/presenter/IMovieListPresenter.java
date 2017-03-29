package com.example.storm.moviesinfo.presenter;

import com.example.storm.moviesinfo.view.fragment.MovieListFragment;

/**
 * Created by khb on 2017/3/28.
 */

public interface IMovieListPresenter {
    void register(MovieListFragment fragment);
    void loadData(String city, int dataType);
    void unregister();

}
