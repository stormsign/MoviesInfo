package com.example.storm.moviesinfo.net;

import com.example.storm.moviesinfo.model.movieinfo.MovieResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by khb on 2017/3/14.
 */
public interface MovieRequestService {
    @GET("video")
    Call<MovieResponse> getMovieInfo(@Query("dtype") String type,
                                     @Query("q") String name,
                                     @Query("key") String key);

    @FormUrlEncoded
    @POST("video")
    Call<MovieResponse> getMovieInfoPost(@Field("dtype") String type,
                                         @Field("q") String name,
                                         @Field("key") String key);

    @FormUrlEncoded
    @POST("video")
    Call<MovieResponse> getMovieInfoPost2(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("video")
    Observable<MovieResponse> getMovieInfoPostRxJava(@FieldMap Map<String, Object> map);

//    Observable
}
