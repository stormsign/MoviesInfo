package com.example.storm.moviesinfo.presenter.impl;

import android.util.Log;

import com.example.storm.moviesinfo.model.movielist.MovieBrief;
import com.example.storm.moviesinfo.model.movielist.MovieListResponse;
import com.example.storm.moviesinfo.net.RequestBuilder;
import com.example.storm.moviesinfo.net.RequestSubscriber;
import com.example.storm.moviesinfo.presenter.IMovieListPresenter;
import com.example.storm.moviesinfo.view.fragment.MovieListFragment;

import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Created by khb on 2017/3/28.
 */

public class MovieListPresenterImpl implements IMovieListPresenter {
    private MovieListFragment fragment;
    private RequestBuilder builder;
    private RequestSubscriber mSubscriber;

    @Override
    public void regist(MovieListFragment fragment) {
        this.fragment = fragment;
        builder = new RequestBuilder();
    }

    @Override
    public void loadData(String city, int dataType) {

        Log.i("Log", "loadData");
        mSubscriber = new RequestSubscriber<List<MovieBrief>>() {
            @Override
            public void onStart() {
                super.onStart();
                Log.i("Log", "onStart");
            }

            @Override
            public void onNext(List<MovieBrief> movieListResponse) {
                super.onNext(movieListResponse);
                Log.i("Log", "onNext");
                Log.i("Log", "movieListResponse = " + movieListResponse.toString());
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                Log.i("Log", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.i("Log", "onError");
                e.printStackTrace();
            }
        };
        if (dataType == 0){
            builder.getMovieInTheaterList(city, mSubscriber);
        }else {
            builder.getMovieInComingList(city);
        }
    }

    @Override
    public void unregist() {
        if (mSubscriber!=null && mSubscriber.isUnsubscribed()){
            mSubscriber.unsubscribe();
        }
        fragment = null;
    }
}
