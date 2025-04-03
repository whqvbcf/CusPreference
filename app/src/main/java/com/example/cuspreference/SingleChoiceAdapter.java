package com.example.cuspreference;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SingleChoiceAdapter extends RecyclerView.Adapter<SingleChoiceAdapter.ViewHolder> {
    private final List<CharSequence> entries;
    private final List<CharSequence> entryValues;
    private String selectedValue;
    private final OnItemSelectedListener listener;

    public SingleChoiceAdapter(List<CharSequence> entries, List<CharSequence> entryValues, 
                             String selectedValue, OnItemSelectedListener listener) {
        this.entries = entries;
        this.entryValues = entryValues;
        this.selectedValue = selectedValue;
        this.listener = listener;
    }

    public void setSelectedValue(String value) {
        this.selectedValue = value;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_radio_button, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(entries.get(position));
        String value = entryValues.get(position).toString();
        holder.radioButton.setTag(value);
        holder.radioButton.setChecked(value.equals(selectedValue));
        
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemSelected(value);
            }
        });
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;
        final RadioButton radioButton;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.radio_text);
            radioButton = itemView.findViewById(R.id.radio_button);
        }
    }
} 