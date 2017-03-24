package com.example.storm.moviesinfo.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.polaric.colorful.Colorful;
import org.polaric.colorful.ColorfulActivity;

/**
 * Created by khb on 2017/3/24.
 */

public class BaseActivity extends ColorfulActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultTheme();

    }

    private void setDefaultTheme() {
        Colorful.config(this)
                .primaryColor(Colorful.ThemeColor.BROWN)
                .accentColor(Colorful.ThemeColor.DEEP_ORANGE)
                .translucent(true)
                .dark(false).apply();
    }
}
