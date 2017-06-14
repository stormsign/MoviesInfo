package com.example.storm.moviesinfo.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.storm.moviesinfo.R;
import com.example.storm.moviesinfo.view.widget.MyRecyclerview.model.Visitor;
import com.example.storm.moviesinfo.view.widget.MyRecyclerview.viewholder.BaseViewHolder;

import java.util.List;

/**
 * Created by khb on 2017/3/29.
 */

public class MovieListAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private final Context context;
    private List<Visitor> models;
    private String holdText;
    private TypeFactoryForRecyclerView typeFactory;
    private View view;

    private boolean isFirstLoading;  //是否在加载
    private boolean hasNoData;  //是否没有数据

    public MovieListAdapter(Context context, List<Visitor> models){
        this.models = models;
        this.context = context;
        this.typeFactory = new TypeFactoryForRecyclerView();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        BaseViewHolder viewHolder = typeFactory.createViewHolder(viewType, view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (getItemViewType(position)==typeFactory.LISTHOLDER){
            holder.setUpView(holdText, position);
        }else {
            holder.setUpView(models.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        hasNoData = models == null || models.isEmpty();
        return hasNoData ? 1 : models.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (isFirstLoading){
            return typeFactory.LISTHOLDER;
        }else {
            return hasNoData
                    ? typeFactory.LISTHOLDER
                    : models.get(position).type(typeFactory);
        }
    }

    public boolean isFirstLoading() {
        return isFirstLoading;
    }

    public void setFirstLoading(boolean firstLoading) {
        isFirstLoading = firstLoading;
        if (isFirstLoading){
            holdText = context.getResources().getString(R.string.list_loading);
            notifyDataSetChanged();
        }
    }

    public boolean hasNoData(){
        return  hasNoData;
    }

    public void setHasNoData(boolean hasNoData) {
        this.hasNoData = hasNoData;
        if (hasNoData){
            holdText = context.getResources().getString(R.string.list_nodata);
            models.clear();
            notifyDataSetChanged();
        }
    }
}
