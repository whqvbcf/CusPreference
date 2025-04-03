package com.example.cuspreference.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.preference.EditTextPreference;

import com.example.cuspreference.R;

//cursor ：
//继承androidx的editpreference，点击editpreference出现自定义dialog
//上面生成的CustomEditTextPreference，编辑的内容似乎不显示在preference的summary中，能解决一下吗
public class CustomEditTextPreference extends EditTextPreference {

    public CustomEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onClick() {
        // Inflate the custom dialog layout
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View dialogView = inflater.inflate(R.layout.custom_dialog, null);

        // Find views in the custom dialog layout
        TextView dialogTitle = dialogView.findViewById(R.id.dialog_title);
        EditText dialogEditText = dialogView.findViewById(R.id.dialog_edit_text);

        // Set the current text to the EditText
        dialogEditText.setText(getText());

        // Create and show the dialog
        new AlertDialog.Builder(getContext())
                .setView(dialogView)
                .setPositiveButton("OK", (dialog, which) -> {
                    // Save the text when OK is clicked
                    String newValue = dialogEditText.getText().toString();
                    if (callChangeListener(newValue)) {
                        setText(newValue);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        setText(restorePersistedValue ? getPersistedString(getText()) : (String) defaultValue);
    }

    @Override
    public void setText(String text) {
        super.setText(text);
        setSummary(text); // Update the summary with the new text
    }
}
