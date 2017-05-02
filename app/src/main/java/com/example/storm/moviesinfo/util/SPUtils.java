package com.example.storm.moviesinfo.util;

import com.example.storm.moviesinfo.App;
import com.example.storm.moviesinfo.R;

/**
 * Created by khb on 2017/5/2.
 */

public class SPUtils {

    public static boolean saveCity(String city){
        return SPHelper.saveString(App.getAppContext().getString(R.string.key_city), city);
    }

    public static String getCity(){
        return SPHelper.getString(App.getAppContext().getString(R.string.key_city),"");
    }
}
