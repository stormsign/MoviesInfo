package com.example.storm.moviesinfo.presenter.impl;

import android.text.TextUtils;
import android.util.Log;

import com.example.storm.moviesinfo.model.movielist.MovieBrief;
import com.example.storm.moviesinfo.net.RequestBuilder;
import com.example.storm.moviesinfo.net.RequestSubscriber;
import com.example.storm.moviesinfo.presenter.IMovieListPresenter;
import com.example.storm.moviesinfo.util.SPUtils;
import com.example.storm.moviesinfo.view.fragment.MovieListFragment;

import java.util.List;

import rx.Observable;

/**
 * Created by khb on 2017/3/28.
 */

public class MovieListPresenterImpl implements IMovieListPresenter {
    private MovieListFragment fragment;
    private RequestBuilder builder;
    private RequestSubscriber mSubscriber;
    private RequestSubscriber mLocationSubscriber;

    @Override
    public void register(MovieListFragment fragment) {
        this.fragment = fragment;
        builder = new RequestBuilder();
    }

    @Override
    public void loadData(int dataType) {
        Log.i("Log", "loadData");
        String city = SPUtils.getCity();
        if (TextUtils.isEmpty(city)){
            fragment.onLocatingFailed();
        }else {
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
                    fragment.onLoadData(movieListResponse);
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
//                if (e instanceof ResultException){
                    fragment.onLoadFailed(0, null);
//                }
                }
            };

            Observable<List<MovieBrief>> observable;
            if (dataType == 0) {
                observable = builder.getMovieInTheaterList(city);
            } else {
                observable = builder.getMovieInComingList(city);
            }
            observable.subscribe(mSubscriber);
        }


    }

    public void getLocation(){
        mLocationSubscriber = new RequestSubscriber<String>(){
            @Override
            public void onNext(String s) {
                super.onNext(s);
                SPUtils.saveCity(s);
                loadData(fragment.getDataType());
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.i("Log", "OnError locating failed");
                e.printStackTrace();
                fragment.hideLocatingDialog();
            }
        };
        Observable<String> locationCity = builder.getLocationCity();
        locationCity.subscribe(mLocationSubscriber);
    }


    @Override
    public void unregister() {
        if (mSubscriber!=null && mSubscriber.isUnsubscribed()){
            mSubscriber.unsubscribe();
        }
        fragment = null;
    }
}
