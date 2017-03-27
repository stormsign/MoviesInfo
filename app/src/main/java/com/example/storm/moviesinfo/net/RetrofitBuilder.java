package com.example.storm.moviesinfo.net;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.storm.moviesinfo.model.movieinfo.MovieResponse;
import com.example.storm.moviesinfo.util.Constants;

import java.util.HashMap;
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
import rx.schedulers.Schedulers;

/**
 * Created by khb on 2017/3/14.
 */
public class RetrofitBuilder  {

    private final Retrofit retrofit;
    private final MovieRequestService service;
    private Call<MovieResponse> movieInfoCall;

    public RetrofitBuilder(){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://op.juhe.cn/onebox/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        service = retrofit.create(MovieRequestService.class);
    }

    public void getMovie(String name){
        movieInfoCall = service.getMovieInfo("json", name, "39cabbbfad378ad59b2bbd0c9cb17897");
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
        Call<MovieResponse> movieInfoPost = service.getMovieInfoPost("json", name, "39cabbbfad378ad59b2bbd0c9cb17897");
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
        map.put("key", "39cabbbfad378ad59b2bbd0c9cb17897");
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

}
