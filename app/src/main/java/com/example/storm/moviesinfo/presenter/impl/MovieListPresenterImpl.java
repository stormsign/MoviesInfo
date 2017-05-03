package com.example.storm.moviesinfo.presenter.impl;

import android.text.TextUtils;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.storm.moviesinfo.App;
import com.example.storm.moviesinfo.model.movielist.MovieBrief;
import com.example.storm.moviesinfo.net.MovieRequestService;
import com.example.storm.moviesinfo.net.RequestBuilder;
import com.example.storm.moviesinfo.net.RequestSubscriber;
import com.example.storm.moviesinfo.presenter.IMovieListPresenter;
import com.example.storm.moviesinfo.util.Constants;
import com.example.storm.moviesinfo.util.SPUtils;
import com.example.storm.moviesinfo.view.fragment.MovieListFragment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by khb on 2017/3/28.
 */

public class MovieListPresenterImpl implements IMovieListPresenter {
    private MovieListFragment fragment;
    private RequestBuilder builder;
    private RequestSubscriber mSubscriber;

    @Override
    public void register(MovieListFragment fragment) {
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
        if (dataType == 0){
            observable = builder.getMovieInTheaterList(city);
        }else {
            observable = builder.getMovieInComingList(city);
        }
        observable.subscribe(mSubscriber);

        //===============================================================

        final MovieRequestService service = new Retrofit.Builder()
                .baseUrl(Constants.JUHE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build().create(MovieRequestService.class);
        Observable<String> stringObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                //检查city值是否存在
                String city = SPUtils.getCity();
                subscriber.onNext(city);
                subscriber.onCompleted();
            }
        });
        stringObservable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Func1<String, Observable<Object>>() {
                    @Override
                    public Observable<Object> call(String s) {
                        if (TextUtils.isEmpty(s)){  //没有设置城市就开始定位
                            AMapLocationClient client = new AMapLocationClient(App.getAppContext());
                            AMapLocationClientOption option = new AMapLocationClientOption();
                            option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
                            option.setOnceLocation(true);
                            client.setLocationOption(option);
                            client.setLocationListener(new AMapLocationListener() {
                                @Override
                                public void onLocationChanged(AMapLocation aMapLocation) {
//                                    subscriber.onNext(aMapLocation.getCity());
//                                    subscriber.onCompleted();
                                }
                            });

                        }
                        Map<String, Object> map = new HashMap<>();
                        map.put("city", s);
                        map.put("key", Constants.JUHE_KEY);
//                        return service.getRecentMovieList(map);

                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public void loadData(int dataType) {
        Log.i("Log", "loadData");
        if (TextUtils.isEmpty(SPUtils.getCity())){
            fragment.showPositioningDialog();

        }
        String city = SPUtils.getCity();
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
        if (dataType == 0){
            observable = builder.getMovieInTheaterList(city);
        }else {
            observable = builder.getMovieInComingList(city);
        }
        observable.subscribe(mSubscriber);
    }

    @Override
    public void unregister() {
        if (mSubscriber!=null && mSubscriber.isUnsubscribed()){
            mSubscriber.unsubscribe();
        }
        fragment = null;
    }
}
