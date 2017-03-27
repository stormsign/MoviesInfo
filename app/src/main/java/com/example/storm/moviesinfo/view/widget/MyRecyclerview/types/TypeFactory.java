package com.example.storm.moviesinfo.view.widget.MyRecyclerview.types;

import android.view.View;

import com.example.storm.moviesinfo.view.widget.MyRecyclerview.viewholder.BaseViewHolder;

/**
 * Created by khb on 2017/3/7.
 */
public interface TypeFactory {
//    int type(TypeOne typeOne);
//    int type(TypeTwo typeTwo);
//    int type(TypeThree typeThree);

    BaseViewHolder createViewHolder(int type, View itemView);
}
