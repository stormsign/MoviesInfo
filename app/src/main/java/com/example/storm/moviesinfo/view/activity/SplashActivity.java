package com.example.storm.moviesinfo.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.storm.moviesinfo.R;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 10097 on 2017/3/18.
 */

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.splash_root)
    RelativeLayout mRootView;
    @BindView(R.id.splash_bg_img)
    KenBurnsView mBgImg;

    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            //为什么这里先清除一遍？
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            //保持界面稳定布局？？
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            //以下两行为5.0上透明状态栏，避免了某些情况下出现半透明黑色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        RandomTransitionGenerator generator = new RandomTransitionGenerator(20000, new LinearInterpolator());
        mBgImg.setTransitionGenerator(generator);
        Glide.with(this).load(R.mipmap.pic_cinema).into(mBgImg);

        mHandler = new Handler();
        jumpToMain();
    }

    private void jumpToMain() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                MainActivity.navigation(SplashActivity.this);
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        }, 1000);
    }
}
