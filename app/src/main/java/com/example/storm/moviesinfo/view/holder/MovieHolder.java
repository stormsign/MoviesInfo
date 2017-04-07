package com.example.storm.moviesinfo.view.holder;

import android.view.View;
import android.widget.TextView;

import com.example.storm.moviesinfo.R;
import com.example.storm.moviesinfo.model.movielist.MovieBrief;
import com.example.storm.moviesinfo.view.widget.MyRecyclerview.viewholder.BaseViewHolder;

/**
 * Created by khb on 2017/3/29.
 */

public class MovieHolder extends BaseViewHolder<MovieBrief> {

    private View itemView;

    public MovieHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    @Override
    public void setUpView(MovieBrief model, int position) {
//        Glide.with(itemView.getContext()).load(model.getIconaddress())
//        .into((ImageView) getView(R.id.poster));
        ((TextView)getView(R.id.movieName)).setText(model.getTvTitle());
    }
}
