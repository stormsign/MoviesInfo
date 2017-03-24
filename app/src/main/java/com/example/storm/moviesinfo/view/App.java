package com.example.storm.moviesinfo.view;

import android.app.Application;

import org.polaric.colorful.Colorful;

/**
 * Created by khb on 2017/3/24.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Colorful.init(this);
    }
}
