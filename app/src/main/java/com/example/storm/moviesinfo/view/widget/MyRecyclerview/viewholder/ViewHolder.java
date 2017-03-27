package com.example.storm.moviesinfo.view.widget.MyRecyclerview.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by khb on 2017/2/13.
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private View mConvertView;
    private SparseArray<View> mViews;

    public ViewHolder(View itemView) {
        super(itemView);
    }

    public ViewHolder(Context context, View itemView){
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<View>();
    }

    public static ViewHolder createViewHolder(Context context, View itemView){
        ViewHolder holder = new ViewHolder(context, itemView);
        return holder;
    }

    public static ViewHolder createViewHolder(Context context, int layoutId, ViewGroup parent){
        return new ViewHolder(context,
                LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    public <T extends View> T getView(int viewId){
        View view = mViews.get(viewId);
        if (view == null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public View getConvertView(){
        return mConvertView;
    }

}
