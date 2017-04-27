package com.example.storm.moviesinfo.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.storm.moviesinfo.R;

import butterknife.ButterKnife;

/**
 * Created by khb on 2017/4/25.
 */

public class BoxOfficeActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box_office);
        ButterKnife.bind(this);
    }
}
