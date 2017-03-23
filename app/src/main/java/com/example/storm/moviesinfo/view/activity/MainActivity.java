package com.example.storm.moviesinfo.view.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.storm.moviesinfo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  {

    @BindView(R.id.main_drawer)
    DrawerLayout mDrawer;
    @BindView(R.id.main_appbar)
    AppBarLayout mAppBar;
    @BindView(R.id.main_toolbar)
    Toolbar mToolBar;
    @BindView(R.id.main_nav)
    NavigationView mNav;

    private boolean isAppBarCollapsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolBar);

        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                isAppBarCollapsed = verticalOffset>0;   //appbar是否被折叠，作为是否需要回到顶端的依据
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.main_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //toolbar左侧的material design风格箭头
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolBar,
                R.string.openDrawerContentDesc, R.string.closeDrawerContentDesc);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        //左侧抽屉导航栏
        ImageView drawerHeaderImg = (ImageView) mNav.getHeaderView(0).findViewById(R.id.drawer_header_img);
        Glide.with(this).load(R.drawable.pic_movies);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
