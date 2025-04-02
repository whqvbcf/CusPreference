package com.example.cuspreference.preference;

import android.content.Context;
import android.graphics.Color;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceViewHolder;

import com.example.cuspreference.R;
import com.example.cuspreference.widget.ScrollingTextViewTouchListener;

public class MyPreferenceCategory extends PreferenceCategory {

    public MyPreferenceCategory(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayoutResource(R.layout.layout_preference_category);
    }
    private void setView(View view) {
        //setShouldDisableView(false);
    }
    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
//        holder.findViewById(R.id.pre_layout).setBackgroundColor(););
        //holder.findViewById(R.id.pre_layout).setBackgroundColor(Color.parseColor("#FF0000"));

    }
}