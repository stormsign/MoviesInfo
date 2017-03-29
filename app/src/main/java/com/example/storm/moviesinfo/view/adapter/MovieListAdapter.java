package com.example.storm.moviesinfo.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.storm.moviesinfo.view.widget.MyRecyclerview.model.Visitor;
import com.example.storm.moviesinfo.view.widget.MyRecyclerview.types.TypeFactoryForRecyclerView;
import com.example.storm.moviesinfo.view.widget.MyRecyclerview.viewholder.BaseViewHolder;

import java.util.List;

/**
 * Created by khb on 2017/3/29.
 */

public class MovieListAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private List<Visitor> models;
    private TypeFactoryForRecyclerView typeFactory;

    public MovieListAdapter(List<Visitor> models){
        this.models = models;
        this.typeFactory = new TypeFactoryForRecyclerView();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
