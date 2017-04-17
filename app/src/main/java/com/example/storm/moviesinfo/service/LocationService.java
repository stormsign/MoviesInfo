package com.example.storm.moviesinfo.service;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.example.storm.moviesinfo.R;

import java.util.List;

/**
 * Created by khb on 2017/3/31.
 */

public class LocationService extends Service {

    private double lat;
    private double lon;
    private String locationMsg;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        getLocation();
        Log.i("Log", "onBind");
        return locationBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("Log", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i("Log", "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onCreate() {
        Log.i("Log", "onCreate");
        super.onCreate();
    }

    private void getLocation(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED){
                locationMsg = getResources().getString(R.string.location_no_permission);
                Log.i("Log", locationMsg);
                return ;
            }
        }
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        List<String> providers = lm.getProviders(true);
        String locationProvider;
        if (providers.contains(LocationManager.NETWORK_PROVIDER)){     //优先使用网络定位
            locationProvider = LocationManager.NETWORK_PROVIDER;
        }else if (providers.contains(LocationManager.GPS_PROVIDER)){
            locationProvider = LocationManager.GPS_PROVIDER;
        }else {
            locationProvider = LocationManager.PASSIVE_PROVIDER;
        }
        lm.requestLocationUpdates(locationProvider, 500, 0, new LocationListener() {
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
        });


    }

    ILocationService.Stub locationBinder = new ILocationService.Stub() {
        @Override
        public String notifyLocation() throws RemoteException {
            return locationMsg;
        }
    };

}
