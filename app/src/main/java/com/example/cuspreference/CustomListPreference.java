package com.example.cuspreference;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.ListPreference;

public class CustomListPreference extends ListPreference {
    public CustomListPreference(Context context) {
        super(context);
    }

    public CustomListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomListPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onClick() {
        CustomListPreferenceDialog dialog = new CustomListPreferenceDialog(getContext(), this);
        dialog.show();
    }

    @Override
    public CharSequence getSummary() {
        CharSequence entry = getEntry();
        return entry != null ? entry : super.getSummary();
    }

    @Override
    public void setValue(String value) {
        super.setValue(value);
        notifyChanged();
    }
} 