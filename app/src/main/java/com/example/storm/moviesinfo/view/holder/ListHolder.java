package com.example.storm.moviesinfo.view.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.storm.moviesinfo.R;
import com.example.storm.moviesinfo.view.widget.MyRecyclerview.viewholder.BaseViewHolder;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by khb on 2017/4/18.
 */

public class ListHolder extends BaseViewHolder<String> {
    public ListHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(String model, int position) {
        ((TextView)itemView.findViewById(R.id.statsTxt)).setText(model);
        AVLoadingIndicatorView animation = (AVLoadingIndicatorView) itemView.findViewById(R.id.loadingAnimation);
        ImageView errorImg = (ImageView) itemView.findViewById(R.id.errorImg);
        if (model.equals(itemView.getContext().getResources().getString(R.string.list_nodata))){
            animation.setVisibility(View.GONE);
            errorImg.setVisibility(View.VISIBLE);
        }else if (model.equals(itemView.getContext().getResources().getString(R.string.list_loading))){
            animation.setVisibility(View.VISIBLE);
            errorImg.setVisibility(View.GONE);
        }
    }
}
