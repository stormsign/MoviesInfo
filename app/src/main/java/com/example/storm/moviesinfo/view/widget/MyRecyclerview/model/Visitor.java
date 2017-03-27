package com.example.storm.moviesinfo.view.widget.MyRecyclerview.model;

import com.example.storm.moviesinfo.view.widget.MyRecyclerview.types.TypeFactory;

/**
 * Created by khb on 2017/3/7.
 */
public interface Visitor {
    int type(TypeFactory typeFactory);
}
