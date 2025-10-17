package com.example.cuspreference.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.core.content.res.TypedArrayUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

import com.example.cuspreference.R;
import com.example.cuspreference.widget.ScrollingTextViewTouchListener;

public class LayoutPreference extends Preference {
    private final View.OnClickListener mClickListener = v -> performClick(v);
    private boolean mAllowDividerAbove;
    private boolean mAllowDividerBelow;

    View mRootView;

    public LayoutPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0 /* defStyleAttr */);
    }

    public LayoutPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    public LayoutPreference(Context context, int resource) {
        this(context, LayoutInflater.from(context).inflate(resource, null, false));
    }

    public LayoutPreference(Context context, View view) {
        super(context);
        setView(view);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Preference);
        mAllowDividerAbove = TypedArrayUtils.getBoolean(a, R.styleable.Preference_allowDividerAbove,
                R.styleable.Preference_allowDividerAbove, false);
        mAllowDividerBelow = TypedArrayUtils.getBoolean(a, R.styleable.Preference_allowDividerBelow,
                R.styleable.Preference_allowDividerBelow, false);
        a.recycle();

        a = context.obtainStyledAttributes(
                attrs, R.styleable.Preference, defStyleAttr, 0);
        int layoutResource = a.getResourceId(R.styleable.Preference_layout,
                0);
        if (layoutResource == 0) {
            throw new IllegalArgumentException("LayoutPreference requires a layout to be defined");
        }
        a.recycle();

        // Need to create view now so that findViewById can be called immediately.
        final View view = LayoutInflater.from(getContext())
                .inflate(layoutResource, null, false);
        setView(view);
    }

    private void setView(View view) {
        setLayoutResource(R.layout.layout_preference_frame);
//        final ViewGroup allDetails = view.findViewById(R.id.all_details);
//        if (allDetails != null) {
//            Utils.forceCustomPadding(allDetails, true /* additive padding */);
//        }
        mRootView = view;
        setShouldDisableView(false);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        holder.itemView.setOnClickListener(mClickListener);

        final boolean selectable = isSelectable();
        holder.itemView.setFocusable(selectable);
        holder.itemView.setClickable(selectable);
        holder.setDividerAllowedAbove(mAllowDividerAbove);
        holder.setDividerAllowedBelow(mAllowDividerBelow);

        FrameLayout layout = (FrameLayout) holder.itemView;
        layout.removeAllViews();
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        layout.addView(mRootView);
//        final TextView viewtext = holder.itemView.findViewById(android.R.id.summary);
//        viewtext.setMovementMethod(ScrollingMovementMethod.getInstance());
//        viewtext.setOnTouchListener(new ScrollingTextViewTouchListener());
    }

    public <T extends View> T findViewById(int id) {
        return mRootView.findViewById(id);
    }
}
