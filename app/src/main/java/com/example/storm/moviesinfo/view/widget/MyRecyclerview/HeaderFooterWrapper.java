package com.example.storm.moviesinfo.view.widget.MyRecyclerview;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

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

    public int getFootersCount() {
        return mFooterViews.size();
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public void addHeaderView(View view){
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    public void addFooterView(View view){
        mFooterViews.put(mFooterViews.size() + BASE_ITEM_TYPE_FOOTER, view);
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
}
