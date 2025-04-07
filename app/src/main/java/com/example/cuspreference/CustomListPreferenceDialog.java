package com.example.cuspreference;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.preference.ListPreference;
import java.util.Arrays;
import java.util.List;

public class CustomListPreferenceDialog extends Dialog {
    private final ListPreference preference;
    private String selectedValue;
    private SingleChoiceAdapter adapter;

    public CustomListPreferenceDialog(Context context, ListPreference preference) {
        super(context);
        this.preference = preference;
        this.selectedValue = preference.getValue();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_list_preference_dialog);

        Window window = getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setGravity(Gravity.BOTTOM);
            // 设置底部边距
            WindowManager.LayoutParams params = window.getAttributes();
            params.y = 20; // 距离底部20dp
            window.setAttributes(params);
        }

        TextView titleView = findViewById(R.id.dialog_title);
        titleView.setText(preference.getTitle());

        ImageButton closeButton = findViewById(R.id.close_button);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        // 设置RecyclerView
        List<CharSequence> entries = Arrays.asList(preference.getEntries());
        List<CharSequence> entryValues = Arrays.asList(preference.getEntryValues());
        
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new ItemSpacingDecoration(36));
        
        adapter = new SingleChoiceAdapter(entries, entryValues, selectedValue, value -> {
            selectedValue = value;
            adapter.setSelectedValue(value);
            if (selectedValue != null) {
                if (preference.callChangeListener(selectedValue)) {
                    preference.setValue(selectedValue);
                }
            }
            dismiss();
        });
        
        recyclerView.setAdapter(adapter);

        closeButton.setOnClickListener(v -> dismiss());
    }
} 