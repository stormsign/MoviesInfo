package com.example.storm.moviesinfo.presenter.impl;

import android.util.Log;

import com.example.storm.moviesinfo.model.movielist.MovieListResponse;
import com.example.storm.moviesinfo.net.RequestBuilder;
import com.example.storm.moviesinfo.net.RequestSubscriber;
import com.example.storm.moviesinfo.presenter.IMovieListPresenter;
import com.example.storm.moviesinfo.view.fragment.MovieListFragment;

import rx.Observable;

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
        Observable<MovieListResponse> observable;
        if (dataType == 0){
            observable = builder.getMovieInTheaterList(city);
        }else {
            observable = builder.getMovieInComingList(city);
        }
        mSubscriber = new RequestSubscriber<MovieListResponse>(){
            @Override
            public void onStart() {
                super.onStart();
                Log.i("Log", "onStart");
            }

            @Override
            public void onNext(MovieListResponse movieListResponse) {
                super.onNext(movieListResponse);
                Log.i("Log", "onNext");
                Log.i("Log", "movieListResponse = "+movieListResponse.toString());
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
//        observable.subscribe(mSubscriber);
    }

    @Override
    public void unregist() {
        if (mSubscriber!=null && mSubscriber.isUnsubscribed()){
            mSubscriber.unsubscribe();
        }
        fragment = null;
    }
}
