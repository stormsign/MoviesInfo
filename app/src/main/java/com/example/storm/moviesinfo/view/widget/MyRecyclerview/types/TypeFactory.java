package com.example.storm.moviesinfo.view.widget.MyRecyclerview.types;

import android.view.View;

import com.example.storm.moviesinfo.model.movielist.MovieBrief;
import com.example.storm.moviesinfo.view.holder.ListHolder;
import com.example.storm.moviesinfo.view.widget.MyRecyclerview.viewholder.BaseViewHolder;

/**
 * Created by khb on 2017/3/7.
 */
public interface TypeFactory {
    int type(MovieBrief movieBrief);
//    int type(TypeTwo typeTwo);
//    int type(TypeThree typeThree);
    int type(ListHolder listHolder);

    BaseViewHolder createViewHolder(int type, View itemView);
}
