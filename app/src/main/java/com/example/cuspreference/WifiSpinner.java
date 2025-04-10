package com.example.cuspreference;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

public class WifiSpinner extends Spinner {

    public WifiSpinner(Context context) {
        super(context);
    }

    public WifiSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WifiSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            try {
                // 反射获取 mPopup
                java.lang.reflect.Field popup = Spinner.class.getDeclaredField("mPopup");
                popup.setAccessible(true);

                // 获取 PopupWindow
                android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(this);
                if (popupWindow != null) {
                    // 设置偏移量，使弹框在 Spinner 下方
                    popupWindow.setVerticalOffset(this.getHeight());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
} 