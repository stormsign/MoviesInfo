package com.example.storm.moviesinfo.view.widget.MyRecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by khb on 2017/4/28.
 */

public class ItemClickListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener onClickListener;
    private GestureDetector gestureDetector;

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public ItemClickListener(Context context, final MyRecyclerView recyclerView, OnItemClickListener onItemClickListener){

        this.onClickListener = onItemClickListener;

        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                int position = recyclerView.getChildLayoutPosition(child) - recyclerView.getWrapper().getHeadersCount();
                if (child!=null && onClickListener != null){
                    onClickListener.onItemClick(child, position);
                    return true;
                }
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        if (gestureDetector.onTouchEvent(e)){
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
