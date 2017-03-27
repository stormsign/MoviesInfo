package com.example.storm.moviesinfo.view.widget.MyRecyclerview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.storm.moviesinfo.R;

/**
 * Created by khb on 2017/2/20.
 */
public class MyRecyclerView extends RecyclerView {

    private HeaderFooterWrapper wrapper;
    private View header;
    private View footer;
    private final int MAX_HEADERHEIGHT = 800;
    private final int FIX_HEADERHEIGHT = 300;
    private final int BUFFER_HEIGHT = 200;
    private int headerFinalHeight;

    private int header_status;

    private final int HEADER_HIDE = 0;
    private final int HEADER_SHOW = 1;
    private final int HEADER_LOADING = 2;

    private final int PAGESIZE = 10;

    public boolean hasHeader;
    public boolean hasFooter;
    boolean footerEnable;
    boolean headerEnable;

    public boolean scrolldown = false;
    private MyScrollListener myScrollListener;

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (adapter instanceof HeaderFooterWrapper){
            wrapper = (HeaderFooterWrapper) adapter;
        }
        header = wrapper.getHeaderView();
        ViewGroup.LayoutParams params = header.getLayoutParams();
        params.height = 0;
        headerFinalHeight = 0;
        header.setLayoutParams(params);
        footer = wrapper.getFooterView();
        myScrollListener = new MyScrollListener();
        addOnScrollListener(myScrollListener);
        super.setAdapter(adapter);
    }

    private float startY;
    private float endY;
    private float dy;
    private float headerHeight;

    @Override
    public boolean onTouchEvent(MotionEvent e) {

        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                startY = e.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                endY = e.getRawY();
                dy = endY - startY;

                if (dy > 0) {
                    if (header_status == HEADER_HIDE && dy >= BUFFER_HEIGHT && dy <= MAX_HEADERHEIGHT + BUFFER_HEIGHT) {
                        headerHeight = dy - BUFFER_HEIGHT;
                        if (header != null) {
                            ViewGroup.LayoutParams layoutParams = header.getLayoutParams();
                            layoutParams.height = (int) headerHeight;
                            header.setLayoutParams(layoutParams);
                        }
                    } else if (header_status == HEADER_SHOW) {
                        if (dy + headerHeight <= MAX_HEADERHEIGHT) {
                            if (header != null) {
                                ViewGroup.LayoutParams layoutParams = header.getLayoutParams();
                                layoutParams.height = (int) (headerHeight + dy);
                                header.setLayoutParams(layoutParams);
                            }
                        }else {
                            headerHeight = MAX_HEADERHEIGHT;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (header_status == HEADER_HIDE){
                    if (dy >= BUFFER_HEIGHT){
                        if (dy >= FIX_HEADERHEIGHT + BUFFER_HEIGHT){
                            header_status = HEADER_SHOW;
                        }
                    }
                }else if (header_status == HEADER_SHOW){

                }
                fixHeaderHeight();
                break;
        }
        return super.onTouchEvent(e);
    }

    public void removeHeader(){
        if (header != null){
            header_status = HEADER_HIDE;
            fixHeaderHeight();
        }
        hasHeader = false;
    }

    public void removeFooter(){
        int size = wrapper.getRealItemCount();
//        for (int i = size; i<size+PAGESIZE; i++){
//            list.add(i+"");
//        }
        wrapper.removeFooter();
        hasFooter = false;
    }


    private void fixHeaderHeight(){
        final ViewWrapper headerWrapper = new ViewWrapper(header);
        ValueAnimator heightA = null;
        if (header_status == HEADER_SHOW) {
            heightA =
                    ObjectAnimator.ofFloat(headerWrapper, headerWrapper.HEIGHT,
                            headerWrapper.getV().getLayoutParams().height,
                            FIX_HEADERHEIGHT);
            headerHeight = FIX_HEADERHEIGHT;
        }else if (header_status == HEADER_HIDE){
            heightA =
                    ObjectAnimator.ofFloat(headerWrapper, headerWrapper.HEIGHT, headerHeight, 0);
            headerHeight = 0;
        }
        if (heightA != null) {
            heightA.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    headerWrapper.getV().invalidate();
                }
            });
            heightA.setDuration(200);
            AnimatorSet as = new AnimatorSet();
            as.play(heightA);
            as.start();
        }
    }

    class ViewWrapper {
        public final String HEIGHT = "height";
        View v;
        float height;
        float alpha;

        public ViewWrapper(View v) {
            this.v = v;
        }

        public View getV() {
            return v;
        }

        public float getHeight() {
            return height;
        }

        public void setHeight(float height) {
            this.height = height;
            v.getLayoutParams().height = (int) height;
            v.requestLayout();
        }
    }

    class MyScrollListener extends RecyclerView.OnScrollListener{

        private int lastVisibleItem;
        private int firstVisibleItem;
        int scrollDownThreshold = 0;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && firstVisibleItem == wrapper.getHeadersCount()
                    && !headerEnable
                    ) {
                Toast.makeText(getContext(), "到顶了", Toast.LENGTH_SHORT).show();
                headerEnable = true;
            }
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem == wrapper.getItemCount() - 1
                    && firstVisibleItem != 0
                    && !footerEnable
                    && !hasFooter) {
                Toast.makeText(getContext(), "到底了", Toast.LENGTH_SHORT).show();
                footerEnable = true;
            }
            if (newState == RecyclerView.SCROLL_STATE_DRAGGING
                    && lastVisibleItem == wrapper.getItemCount() - 1
                    && firstVisibleItem != 0
                    && footerEnable
                    && !hasFooter){
                wrapper.addFooterView(LayoutInflater.from(getContext()).inflate(R.layout.item_listfooter, recyclerView, false));
                wrapper.notifyItemChanged(wrapper.getHeadersCount() + wrapper.getRealItemCount() + wrapper.getFootersCount());
                hasFooter = true;
                footerEnable = false;
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem =
                    ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            firstVisibleItem =
                    ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

            boolean isSignificantDelta = Math.abs(dy) > scrollDownThreshold;
            if (isSignificantDelta){
                //pagedown
                if (dy > 0) {
                }else {
                }
            }else {
            }
        }

    }

}
