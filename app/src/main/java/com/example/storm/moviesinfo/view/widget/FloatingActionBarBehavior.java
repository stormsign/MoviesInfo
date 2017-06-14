package com.example.storm.moviesinfo.view.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by khb on 2017/4/21.
 */

public class FloatingActionBarBehavior extends FloatingActionButton.Behavior {

    public FloatingActionBarBehavior(Context context, AttributeSet attributeSet){
        super();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return ViewCompat.SCROLL_AXIS_VERTICAL == nestedScrollAxes;     //监听垂直滑动
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if ((dyConsumed > 0 && dyUnconsumed == 0) || (dyConsumed == 0
                && dyUnconsumed > 0)){    //上滑监听
            child.show();
        }else if ((dyConsumed < 0 && dyUnconsumed == 0) || (dyConsumed == 0
                && dyUnconsumed < 0)){      //下滑监听
            child.hide();
        }
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target) {

        super.onStopNestedScroll(coordinatorLayout, child, target);
    }
}
