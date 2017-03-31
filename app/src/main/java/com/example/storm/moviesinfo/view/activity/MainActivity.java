package com.example.storm.moviesinfo.view.activity;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.storm.moviesinfo.R;
import com.example.storm.moviesinfo.service.ILocationService;
import com.example.storm.moviesinfo.view.adapter.TabAdapter;
import com.example.storm.moviesinfo.view.fragment.MovieListFragment;

import org.polaric.colorful.Colorful;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements ServiceConnection {

    @BindView(R.id.main_drawer)
    DrawerLayout mDrawer;
    @BindView(R.id.main_appbar)
    AppBarLayout mAppBar;
    @BindView(R.id.main_toolbar)
    Toolbar mToolBar;
    @BindView(R.id.main_nav)
    NavigationView mNav;
    @BindView(R.id.main_tab)
    TabLayout mTab;
    @BindView(R.id.main_viewpager)
    ViewPager mPager;

    private boolean isAppBarCollapsed;
    private SwitchCompat mSwitch;

    private double lat;
    private double lon;
    private String locationMsg;

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

        mTab.setBackgroundResource(Colorful.getThemeDelegate().getPrimaryColor().getColorRes());
        MovieListFragment fragment0 = MovieListFragment.newInstance(0);
        MovieListFragment fragment1 = MovieListFragment.newInstance(1);
        Fragment[] fragments = new Fragment[]{fragment0, fragment1};
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), fragments);
        adapter.setPageTitle(new String[]{"正在上映", "即将上映"});
        mPager.setAdapter(adapter);
        mTab.setupWithViewPager(mPager);


        //左侧抽屉导航栏
        ImageView drawerHeaderImg = (ImageView) mNav.getHeaderView(0).findViewById(R.id.drawer_header_img);
        Glide.with(this).load(R.drawable.pic_movies).into(drawerHeaderImg);
        mSwitch = (SwitchCompat) mNav.getHeaderView(0).findViewById(R.id.dayNightSwitch);
        mSwitch.setChecked(!Colorful.getThemeDelegate().isDark());
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Colorful.config(MainActivity.this).dark(!isChecked).apply();
                        recreate();
                    }
                }, 300);

            }
        });
//        Intent intent = new Intent(this, LocationService.class);
//        bindService(intent, this, BIND_AUTO_CREATE);

        /*LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        List<String> providers = lm.getProviders(true);
        String locationProvider;
        if (providers.contains(LocationManager.NETWORK_PROVIDER)){     //优先使用网络定位
            locationProvider = LocationManager.NETWORK_PROVIDER;
        }else if (providers.contains(LocationManager.GPS_PROVIDER)){
            locationProvider = LocationManager.GPS_PROVIDER;
        }else {
            locationProvider = LocationManager.PASSIVE_PROVIDER;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED){
                locationMsg = getResources().getString(R.string.location_no_permission);
                Log.i("Log", locationMsg);
                return ;
            }
        }
        lm.requestLocationUpdates(locationProvider, 3000, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location!=null){
                    lat = location.getLatitude();
                    lon = location.getLongitude();
                    Log.i("Log", "lat="+lat +" -- lon="+lon);
                    locationMsg = "lat="+lat +" -- lon="+lon;
                }else {
                    locationMsg = getResources().getString(R.string.location_no_provider);
                    Log.i("Log", locationMsg);
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });*/

    }

    @Override
    protected void onDestroy() {
        unbindService(this);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.i("Log", "onServiceConnected");
        ILocationService locationService = ILocationService.Stub.asInterface(service);
        try {
            Log.i("Log", "notifyLocation "+locationService.notifyLocation());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.i("Log", "onServiceDisconnected");
    }
}
