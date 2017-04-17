package com.example.storm.moviesinfo.view.widget.MyRecyclerview;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.example.storm.moviesinfo.R;
import com.example.storm.moviesinfo.view.widget.MyRecyclerview.viewholder.ViewHolder;

/**
 * Created by khb on 2017/2/13.
 */
public class HeaderFooterWrapper<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int BASE_ITEM_TYPE_HEADER = 100000;
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;
    private RecyclerView.Adapter mInnerAdapter;

    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFooterViews = new SparseArrayCompat<>();

    public static final int HEADER_HIDE = 0;
    public static final int HEADER_SHOW = 1;

    private int header_status;

    public HeaderFooterWrapper(RecyclerView.Adapter adapter){
        mInnerAdapter = adapter;
    }

    public boolean isHeaderViewPos(int position){
        return position<getHeadersCount();
    }

    public boolean isFooterViewPos(int position){
        return position >= getHeadersCount() + getRealItemCount();
    }

    public int getRealItemCount() {
        return mInnerAdapter.getItemCount();
    }

    public void addHeaderView(View view){
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    public void addFooterView(View view){
        mFooterViews.put(mFooterViews.size() + BASE_ITEM_TYPE_FOOTER, view);
        setFooterLoading();
    }

    public View getHeaderView(){
        return mHeaderViews.get(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER - 1);
    }

    public View getFooterView(){
        return mFooterViews.get(mFooterViews.size() + BASE_ITEM_TYPE_FOOTER - 1);
    }

    public void removeHeader(){
        mHeaderViews.remove(mHeaderViews.size() - 1 + BASE_ITEM_TYPE_HEADER);
        notifyItemRemoved(getHeadersCount() - 1);
    }

    public void removeFooter(){
        getFooterView().findViewById(R.id.loading).clearAnimation();
        mFooterViews.remove(mFooterViews.size() - 1 + BASE_ITEM_TYPE_FOOTER);
        notifyItemChanged(getItemCount());
    }

    public RecyclerView.Adapter getInnerAdapter() {
        return mInnerAdapter;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)){
            return mHeaderViews.keyAt(position);
        }else if (isFooterViewPos(position)){
            return mFooterViews.keyAt(position - getHeadersCount() - getRealItemCount());
        }
        return mInnerAdapter.getItemViewType(position - getHeadersCount());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null){
            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(),
                    mHeaderViews.get(viewType));
            return holder;
        } else if (mFooterViews.get(viewType) != null) {
            ViewHolder holder = ViewHolder.createViewHolder(parent.getContext(),
                    mFooterViews.get(viewType));
            return holder;
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isFooterViewPos(position)){
            return ;
        }
        if (isHeaderViewPos(position)){
            return;
        }
        mInnerAdapter.onBindViewHolder(holder, position - getHeadersCount());
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getFootersCount() + getRealItemCount();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        Log.i("Log", "attached");
        if (getHeaderView()!=null
                && getHeaderView().isAttachedToWindow()){
            if (header_status == HEADER_HIDE){
                setHeaderDefault();
            }else {
                setHeaderLoading();
            }
        }
//        if (getFooterView() != null
//                && getFooterView().isAttachedToWindow()){
//            setFooterLoading();
//        }

        super.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        Log.i("Log", "detached");

        super.onViewDetachedFromWindow(holder);
    }

    public int getHeaderStatus(){
        return header_status;
    }

    public void setHeaderStatus(int status){
        header_status = status;
    }

    public void setHeaderDefault(){
        ImageView bar = ((ImageView)getHeaderView().findViewById(R.id.refreshing));
        bar.setImageResource(R.mipmap.img_pointdown);
//        bar.clearAnimation();
//        RotateAnimation animation = new RotateAnimation(180, 0,
//                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
//                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
//        animation.setDuration(500);
//        animation.setFillAfter(true);
//        bar.startAnimation(animation);
    }

    public void setHeaderShow(){
        ImageView bar = ((ImageView)getHeaderView().findViewById(R.id.refreshing));
        bar.setImageResource(R.mipmap.img_pointdown);
        RotateAnimation animation = new RotateAnimation(0, 180,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(200);
        animation.setFillAfter(true);
        bar.startAnimation(animation);

    }

    public void setHeaderHide(){
        ImageView bar = ((ImageView)getHeaderView().findViewById(R.id.refreshing));
        bar.setImageResource(R.mipmap.img_pointdown);
        RotateAnimation animation = new RotateAnimation(180, 0,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(200);
        animation.setFillAfter(true);
        bar.startAnimation(animation);

    }

    public void setHeaderLoading(){
        ImageView bar = ((ImageView)getHeaderView().findViewById(R.id.refreshing));
        bar.setImageResource(R.mipmap.img_refresh);
//            drag.clearAnimation();
        RotateAnimation rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(1000);
        rotate.setRepeatCount(Animation.INFINITE);
        bar.setVisibility(View.VISIBLE);
        bar.startAnimation(rotate);
    }

    public void setFooterLoading(){
        Log.i("Log", " Footer  " + (getFooterView()!= null));
        if (getFooterView()!= null){
            ImageView loading = (ImageView) getFooterView().findViewById(R.id.loading);
            RotateAnimation rotate = new RotateAnimation(0, 360,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(1000);
            rotate.setRepeatCount(Animation.INFINITE);
            loading.startAnimation(rotate);
        }
    }

    public int getFootersCount() {
        return mFooterViews.size();
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

}
