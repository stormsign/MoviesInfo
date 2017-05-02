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

    public RequestBuilder() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.JUHE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        service = retrofit.create(MovieRequestService.class);
    }

    public void getMovie(String name) {
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

    public void getMoviePost(String name) {
        Call<MovieResponse> movieInfoPost = service.getMovieInfoPost("json", name, Constants.JUHE_KEY);
        movieInfoPost.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.i("Log", "POST  " + response.body().toString());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void getMoviePost2(String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("dtype", "json");
        map.put("q", name);
        map.put("key", Constants.JUHE_KEY);
        Call<MovieResponse> movieInfoPost = service.getMovieInfoPost2(map);
        movieInfoPost.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.i("Log", "POST  " + response.body().toString());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void getMovieInfo(final Context context, String name) {
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
    public Observable<List<MovieBrief>> getMovieInTheaterList(String city) {
        Map<String, Object> map = new HashMap<>();
        map.put("city", city);
        map.put("key", Constants.JUHE_KEY);
        Observable<List<MovieBrief>> movieList = service.getRecentMovieList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<MovieListResponse, List<MovieBrief>>() {
                    @Override
                    public List<MovieBrief> call(MovieListResponse movieListResponse) {
                        if (movieListResponse.getError_code() == 0) {   //调用成功
                            MovieListWrapper result = movieListResponse.getResult();
                            if (result != null) {   //数据有效
                                MovieList movieList = result.getData().get(0);
                                if (movieList != null) {   //正在上映电影数据有效
                                    if (movieList.getData() != null && !movieList.getData().isEmpty()) {     //正在上映电影列表不为空
                                        return movieList.getData();
                                    }
                                    throw new ResultException(ResultException.EMPTY,
                                            "数据为空");
                                }
                                throw new ResultException(ResultException.INVALID_DATA,
                                        "数据无效");
                            }
                            throw new ResultException(ResultException.INVALID_DATA,
                                    "数据无效");
                        }
                        throw new ResultException(movieListResponse.getError_code(),
                                movieListResponse.getReason());
                    }
                });
        return movieList;
    }

    //    即将上映电影列表
    public Observable<List<MovieBrief>> getMovieInComingList(String city) {
        Map<String, Object> map = new HashMap<>();
        map.put("city", city);
        map.put("key", Constants.JUHE_KEY);
        Observable<List<MovieBrief>> movieList = service.getRecentMovieList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<MovieListResponse, MovieListWrapper>() {
                    @Override
                    public MovieListWrapper call(MovieListResponse movieListResponse) {
                        return movieListResponse.getResult();
                    }
                })
                .map(new Func1<MovieListWrapper, MovieList>() {
                    @Override
                    public MovieList call(MovieListWrapper movieListWrapper) {
                        return movieListWrapper.getData().get(1);   //即将上映列表
                    }
                })
                .map(new Func1<MovieList, List<MovieBrief>>() {
                    @Override
                    public List<MovieBrief> call(MovieList movieList) {
                        return movieList.getData();
                    }
                });
        return movieList;
    }

    public Observable<String> getLocationName(){



        return null;
    }

}
