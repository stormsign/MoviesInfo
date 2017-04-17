package com.example.storm.moviesinfo.view.adapter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.storm.moviesinfo.view.widget.MyRecyclerview.model.Visitor;
import com.example.storm.moviesinfo.view.widget.MyRecyclerview.viewholder.BaseViewHolder;

import java.util.List;

/**
 * Created by khb on 2017/3/29.
 */

public class MovieListAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private List<Visitor> models;
    private TypeFactoryForRecyclerView typeFactory;
    private View view;

    public MovieListAdapter(List<Visitor> models){
        this.models = models;
        this.typeFactory = new TypeFactoryForRecyclerView();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = View.inflate(parent.getContext(), viewType, null);
        BaseViewHolder viewHolder = typeFactory.createViewHolder(viewType, view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setUpView(models.get(position), position);
        Animator[] animators = new Animator[]{
                ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1f).setDuration(500),
                ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, 200, 0).setDuration(500)
        };
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animators);
        set.start();

    }

    @Override
    public int getItemCount() {
        if (models == null){
            return  0 ;
        }
        return models.size();
    }

    @Override
    public int getItemViewType(int position) {
        return models.get(position).type(typeFactory);
    }
}
