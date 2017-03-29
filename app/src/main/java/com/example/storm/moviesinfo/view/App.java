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
        Colorful.defaults()
                .primaryColor(Colorful.ThemeColor.BROWN)
                .accentColor(Colorful.ThemeColor.DEEP_ORANGE)
                .translucent(false)
                .dark(false);
        Colorful.init(this);
    }
}
