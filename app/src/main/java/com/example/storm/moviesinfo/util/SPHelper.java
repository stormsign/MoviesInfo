package com.example.storm.moviesinfo.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.storm.moviesinfo.App;

/**
 * Created by khb on 2017/5/2.
 */

public class SPHelper {

    private static SharedPreferences mSharedPreferences;

    public static void init(Context context){
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean saveString(String key, String data){
        if (mSharedPreferences==null){
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.getAppContext());
        }
        return mSharedPreferences.edit().putString(key, data).commit();
    }

    public static String getString(String key, String defaultData){
        if (mSharedPreferences==null){
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.getAppContext());
        }
        return mSharedPreferences.getString(key, defaultData);
    }

}
