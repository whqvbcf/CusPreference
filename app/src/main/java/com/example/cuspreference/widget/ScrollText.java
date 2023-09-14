package com.example.cuspreference.widget;

import android.content.Context;
import android.graphics.Rect;
import android.text.Editable;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.ArrowKeyMovementMethod;
import android.text.method.MovementMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 似乎没啥用
 * 出自 https://codeleading.com/article/35314221183/#google_vignette
 * 1.ScrollView嵌套可以滚动的TextView
 * 2,为了解决滑动冲突重写TextView
 */
public class ScrollText extends TextView {

    public Layout mLayout;
    public int paddingTop;
    public int paddingBottom;
    public int mHeight;
    public int mLayoutHeight;
    public int canScrollHeight;
    public int mVert;//可以上滑的高度。初始值为0。最大为canScrollHeight

    public ScrollText(Context context) {
        super(context);
    }

    public ScrollText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mLayout = getLayout();
        mLayoutHeight = mLayout.getHeight();
        paddingTop = getTotalPaddingTop();
        paddingBottom = getTotalPaddingBottom();
        mHeight = getHeight();
        canScrollHeight = mLayoutHeight + paddingTop + paddingBottom - mHeight;
    }


    float lastY;
    float currentY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = event.getY();
            case MotionEvent.ACTION_MOVE:
                currentY = event.getY();
                float dy = currentY - lastY;
                if (dy > 0) {//手指向下滑动
                    if (mVert == 0) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else if (mVert > 0) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                } else if (dy < 0) {
                    if (mVert == canScrollHeight) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else if (mVert < canScrollHeight) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                }
                lastY = currentY;
                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return result;
    }

    @Override
    protected void onScrollChanged(int horiz, int vert, int oldHoriz, int oldVert) {
        super.onScrollChanged(horiz, vert, oldHoriz, oldVert);
        mVert = vert;
    }
}
