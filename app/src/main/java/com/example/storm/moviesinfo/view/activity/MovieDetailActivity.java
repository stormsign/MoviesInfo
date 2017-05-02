package com.example.storm.moviesinfo.view.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.storm.moviesinfo.R;
import com.example.storm.moviesinfo.presenter.IMovieDetailPresenter;
import com.example.storm.moviesinfo.presenter.impl.MovieDetailPresenterImpl;
import com.flaviofaria.kenburnsview.KenBurnsView;

import org.polaric.colorful.Colorful;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.storm.moviesinfo.R.id.fab;

public class MovieDetailActivity extends BaseActivity  {

    @BindView(R.id.collapsing)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fab)
    FloatingActionButton mFab;
    @BindView(R.id.moviePoster)
    KenBurnsView mPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        IMovieDetailPresenter presenter = new MovieDetailPresenterImpl();
        setSupportActionBar(mToolbar);
        String posterUrl = getIntent().getStringExtra("posterUrl");
        Glide.with(this).load(posterUrl).into(mPoster);
        mCollapsingToolbarLayout.setContentScrim(getResources().getDrawable(Colorful.getThemeDelegate().getPrimaryColor().getColorRes()));
        mFab = (FloatingActionButton) findViewById(fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

}
