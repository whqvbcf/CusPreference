package com.example.cuspreference.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * ScrollView 嵌套 ScrollView
 * 1.子ScrollView设置最大高度
 * 2.子ScrollView拦截父空间的触摸事件
 */
public class ScrollViewPro extends ScrollView {

    private int maxHeight = 170;//如果没有设置精确的高度，则最大高度为maxHeight

    public ScrollViewPro(Context context) {
        super(context);
    }

    public ScrollViewPro(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewPro(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY){//如果设置了精确的高度，则使用设置的高度
            super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        }else{
            measureChildren(widthMeasureSpec,heightMeasureSpec);
            View child = getChildAt(0);
            int childHeight = child.getMeasuredHeight();

            ViewGroup.MarginLayoutParams layoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childMarTop = layoutParams.topMargin;
            int childMarBottom = layoutParams.bottomMargin;

            if ((getPaddingTop() + getPaddingBottom() + childMarTop + childMarBottom + childHeight) > maxHeight)
                heightSize = maxHeight;
            super.onMeasure(widthMeasureSpec,MeasureSpec.makeMeasureSpec(heightSize,MeasureSpec.AT_MOST));
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean flag = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_UP:
                flag = super.onInterceptTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_MOVE:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
        }
        return super.onTouchEvent(ev);
    }
}
