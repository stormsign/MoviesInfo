package com.example.storm.moviesinfo.view.iview;

import com.example.storm.moviesinfo.model.movielist.MovieBrief;

import java.util.List;

/**
 * Created by khb on 2017/3/29.
 */

public interface IMovieListFragment {
    void onLoadData(List<MovieBrief> movieList);
    void onLoadFailed(int errCode, String msg);
}
