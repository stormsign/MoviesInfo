package com.example.storm.moviesinfo.view.adapter;

import android.view.View;

import com.example.storm.moviesinfo.R;
import com.example.storm.moviesinfo.model.movielist.MovieBrief;
import com.example.storm.moviesinfo.view.holder.ListHolder;
import com.example.storm.moviesinfo.view.holder.MovieHolder;
import com.example.storm.moviesinfo.view.widget.MyRecyclerview.types.TypeFactory;
import com.example.storm.moviesinfo.view.widget.MyRecyclerview.viewholder.BaseViewHolder;

/**
 * Created by khb on 2017/3/7.
 */
public class TypeFactoryForRecyclerView implements TypeFactory {

    private final int MOVIEBRIEF = R.layout.item_moviebrief;
    public final int LISTHOLDER = R.layout.item_holder;
//    private final int TYPE_TWO = R.layout.item_type_two;
//    private final int TYPE_THREE = R.layout.item_type_three;

//    @Override
//    public int type(TypeOne typeOne) {
//        return TYPE_ONE;
//    }
//
//    @Override
//    public int type(TypeTwo typeTwo) {
//        return TYPE_TWO;
//    }
//
//    @Override
//    public int type(TypeThree typeThree) {
//        return TYPE_THREE;
//    }

    @Override
    public int type(MovieBrief movieBrief) {
        return MOVIEBRIEF;
    }

    @Override
    public int type(ListHolder listHolder){
        return LISTHOLDER;
    }

    @Override
    public BaseViewHolder createViewHolder(int type, View itemView) {
        switch (type){
            case MOVIEBRIEF:
                return new MovieHolder(itemView);
            case LISTHOLDER:
                return new ListHolder(itemView);
//            case TYPE_ONE:
//                return new TypeOneHolder(itemView);
//            case TYPE_TWO:
//                return new TypeTwoHolder(itemView);
//            case TYPE_THREE:
//                return new TypeThreeHolder(itemView);
        }
        return null;
    }
}
