package com.example.storm.moviesinfo.view.holder;

import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.storm.moviesinfo.R;
import com.example.storm.moviesinfo.model.movielist.MovieBrief;
import com.example.storm.moviesinfo.view.widget.AutoColumnLinearLayout;
import com.example.storm.moviesinfo.view.widget.MyRecyclerview.viewholder.BaseViewHolder;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

import org.polaric.colorful.Colorful;

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
    public void setUpView(MovieBrief movie, int position) {
        Glide.with(itemView.getContext()).load(movie.getIconaddress())
                .placeholder(R.drawable.svg_ic_attention)
                .error(R.drawable.svg_ic_attention)
                .into((ImageView) getView(R.id.poster));
        ((TextView)getView(R.id.movieName)).setText(movie.getTvTitle());

        if (movie.getGrade()!=null) {   //设置评分
            SimpleRatingBar ratingBar = (SimpleRatingBar) getView(R.id.stars);
            ratingBar.setVisibility(View.VISIBLE);
            ratingBar.setBorderColor(itemView.getContext().getResources()
                    .getColor(Colorful.getThemeDelegate().getAccentColor().getColorRes()));
            ratingBar.setFillColor(itemView.getContext().getResources()
                    .getColor(Colorful.getThemeDelegate().getAccentColor().getColorRes()));
            ratingBar.setRating(Float.parseFloat(movie.getGrade())/2);
        }else {
            getView(R.id.stars).setVisibility(View.GONE);
        }
        AutoColumnLinearLayout tags = ((AutoColumnLinearLayout)getView(R.id.tags));
        tags.setTotalSpanCount(2);
        String tagsStr = movie.getType().getData().getGroupString();
        String[] tagsArray = tagsStr.split(" ");

        for (int i = 0; i < tagsArray.length; i ++){
            tags.addView(createTagView(tagsArray[i]));
        }

        ((TextView)getView(R.id.premiereTime)).setText(movie.getPlayDate().getShowname()+"："+movie.getPlayDate().getData());
        ((TextView)getView(R.id.director)).setText(movie.getDirector().getShowname() + "："+movie.getDirector().getData().getGroupString());
        ((TextView)getView(R.id.cast)).setText(movie.getStar().getShowname()+"："+movie.getStar().getData().getGroupString());
    }

    //设置影片标签
    private View createTagView(String str){
        TextView tag = new TextView(itemView.getContext());
        tag.setText(str);
        tag.setTextSize(11);
        tag.setPadding(12, 6, 12, 6);
        //代码设置边框
        ShapeDrawable shapeDrawable = new ShapeDrawable();
        Paint paint = shapeDrawable.getPaint();
        paint.setColor(itemView.getContext().getResources()
                .getColor(Colorful.getThemeDelegate().getAccentColor().getColorRes()));
        paint.setStrokeWidth(6);
        paint.setStyle(Paint.Style.STROKE);
        float outerR = 10;
        float innerR = 0;
        RoundRectShape rrs = new RoundRectShape(
                new float[]{outerR, 0, outerR, 0, outerR, 0, outerR, 0},
                null,
                new float[]{innerR, innerR, innerR, innerR, innerR, innerR, innerR, innerR});
        shapeDrawable.setShape(rrs);
        tag.setBackgroundDrawable(shapeDrawable);
        return tag;
    }
}
