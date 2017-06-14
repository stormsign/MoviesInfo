package com.example.storm.moviesinfo.net;

import rx.Observable;

/**
 * Created by khb on 2017/5/2.
 */

public interface LocatingService {

    Observable<String> getLocationName();
}
