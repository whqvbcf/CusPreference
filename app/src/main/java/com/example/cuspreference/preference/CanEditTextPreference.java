package com.example.cuspreference.preference;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.TextView;

import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

import com.example.cuspreference.R;

public class CanEditTextPreference extends Preference {
    private Context mContext;
    private EditText edittext;

    public CanEditTextPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initLayout(context);
    }

    public CanEditTextPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context);
    }

    public CanEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
    }

    public CanEditTextPreference(Context context) {
        super(context);
        initLayout(context);
    }

    private void initLayout(Context context) {
        mContext = context;
        setLayoutResource(R.layout.edit_preference_layout);
    }

    TextView showView;

    public void setShowView(TextView showView) {
        this.showView = showView;
        if (edittext != null && showView != null) {
            showView.setText(edittext.getText().toString());
        }
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        final EditText edittext = holder.itemView.findViewById(R.id.edit_text);
        holder.itemView.findViewById(R.id.direction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showView.setGravity(Gravity.CENTER);
            }
        });
        holder.itemView.findViewById(R.id.left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showView.setGravity(Gravity.START);
            }
        });
        holder.itemView.findViewById(R.id.center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showView.setGravity(Gravity.CENTER);
            }
        });
        holder.itemView.findViewById(R.id.right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showView.setGravity(Gravity.END);
            }
        });
        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (showView != null) {
                    if (s == null) {
                        showView.setText("");
                    } else {
                        showView.setText(s.toString());
                    }
                }
            }
        });

        if (edittext != null && showView != null) {
            showView.setText(edittext.getText().toString());
        }

        edittext.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ViewParent parent = v.getParent();
                if (v instanceof EditText) {
                    EditText editView = (EditText) v;
                    if (editView.getMaxLines() < editView.getLineCount()) {
                        parent.requestDisallowInterceptTouchEvent(true);
                    } else {
                        parent.requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });
    }
}
