package com.example.storm.moviesinfo.net;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.storm.moviesinfo.model.movieinfo.MovieResponse;
import com.example.storm.moviesinfo.model.movielist.MovieBrief;
import com.example.storm.moviesinfo.model.movielist.MovieList;
import com.example.storm.moviesinfo.model.movielist.MovieListResponse;
import com.example.storm.moviesinfo.model.movielist.MovieListWrapper;
import com.example.storm.moviesinfo.util.Constants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by khb on 2017/3/14.
 */
public class RequestBuilder {

    private final Retrofit retrofit;
    private final MovieRequestService service;
    private Call<MovieResponse> movieInfoCall;

    public RequestBuilder(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.JUHE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        service = retrofit.create(MovieRequestService.class);
    }

    public void getMovie(String name){
        movieInfoCall = service.getMovieInfo("json", name, Constants.JUHE_KEY);
        movieInfoCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.i("Log", response.body().toString());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getMoviePost(String name){
        Call<MovieResponse> movieInfoPost = service.getMovieInfoPost("json", name, Constants.JUHE_KEY);
        movieInfoPost.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.i("Log", "POST  "+response.body().toString());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void getMoviePost2(String name){
        Map<String, Object> map = new HashMap<>();
        map.put("dtype", "json");
        map.put("q", name);
        map.put("key", Constants.JUHE_KEY);
        Call<MovieResponse> movieInfoPost = service.getMovieInfoPost2(map);
        movieInfoPost.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.i("Log", "POST  "+response.body().toString());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void getMovieInfo(final Context context, String name){
        Map<String, Object> map = new HashMap<>();
        map.put("dtype", "json");
        map.put("q", name);
        map.put("key", Constants.JUHE_KEY);
        Observable<MovieResponse> movieInfoPostRxJava = service.getMovieInfoPostRxJava(map);
        movieInfoPostRxJava
                .doOnNext(new Action1<MovieResponse>() {
                    @Override
                    public void call(MovieResponse movieResponse) {
                        Log.i("Log", "rxjava    call");
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieResponse>() {

                    private MovieResponse movieResponse;

                    @Override
                    public void onCompleted() {
                        Log.i("Log", "rxjava  onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Log", "rxjava  onError");
                        e.printStackTrace();
                        Toast.makeText(context, movieResponse.getReason(),
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(MovieResponse movieResponse) {
                        Log.i("Log", "POST  " + movieResponse.toString());
                        this.movieResponse = movieResponse;
                        Toast.makeText(context, movieResponse.getResult().getTitle()
                                        + movieResponse.getResult().getYear(),
                                Toast.LENGTH_LONG).show();
                    }
                });
    }

//    正在上映电影列表
    public Observable<MovieListResponse> getMovieInTheaterList(String city){
        Map<String, Object> map = new HashMap<>();
        map.put("dtype", "json");
        map.put("city", city);
        map.put("key", Constants.JUHE_KEY);
        Observable<MovieListResponse> movieList = service.getRecentMovieList(map);
        movieList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new RequestSubscriber<MovieListResponse>(){
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
        });
                /*.map(new Func1<MovieListResponse, MovieListWrapper>() {
                    @Override
                    public MovieListWrapper call(MovieListResponse movieListResponse) {

                        return movieListResponse.getResult();
                    }
                })
                .map(new Func1<MovieListWrapper, MovieList>() {
                    @Override
                    public MovieList call(MovieListWrapper movieListWrapper) {

                        return movieListWrapper.getData().get(0);   //正在上映列表
                    }
                })
                .map(new Func1<MovieList, List<MovieBrief>>() {
                    @Override
                    public List<MovieBrief> call(MovieList movieList) {

                        return movieList.getData();
                    }
                })*/;
        return movieList;
    }
//    即将上映电影列表
    public Observable<MovieListResponse> getMovieInComingList(String city){
        Map<String, Object> map = new HashMap<>();
        map.put("dtype", "json");
        map.put("city", city);
        map.put("key", Constants.JUHE_KEY);
        Observable<MovieListResponse> movieList = service.getRecentMovieList(map);
        movieList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<MovieListResponse, MovieListWrapper>() {
                    @Override
                    public MovieListWrapper call(MovieListResponse movieListResponse) {
                        Log.i("Log", " result="+movieListResponse.toString());
                        return movieListResponse.getResult();
                    }
                })
                .map(new Func1<MovieListWrapper, MovieList>() {
                    @Override
                    public MovieList call(MovieListWrapper movieListWrapper) {
                        Log.i("Log", " result="+movieListWrapper.toString());
                        return movieListWrapper.getData().get(1);   //即将上映列表
                    }
                })
                .map(new Func1<MovieList, List<MovieBrief>>() {
                    @Override
                    public List<MovieBrief> call(MovieList movieList) {
                        Log.i("Log", " result="+movieList.toString());
                        return movieList.getData();
                    }
                });
        return movieList;
    }
}
