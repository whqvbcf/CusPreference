package com.example.cuspreference;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

public class ItemSpacingDecoration extends RecyclerView.ItemDecoration {
    private final int spacing;
    private final Paint dividerPaint;
    private static final int EXTRA_SPACING = 8;
    private static final int DIVIDER_START_MARGIN = 16;
    private static final int DIVIDER_END_MARGIN = 40;

    public ItemSpacingDecoration(int spacing) {
        this.spacing = spacing;
        this.dividerPaint = new Paint();
        this.dividerPaint.setColor(0xFF15181F);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            View child = parent.getChildAt(i);
            int top = child.getBottom() + spacing / 2;
            c.drawRect(
                DIVIDER_START_MARGIN, 
                top, 
                parent.getWidth() - DIVIDER_END_MARGIN, 
                top + 1, 
                dividerPaint
            );
        }
    }

    @Override
    public void getItemOffsets(android.graphics.Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (position == 0) {
            outRect.top = EXTRA_SPACING;
        }
        if (position != state.getItemCount() - 1) {
            outRect.bottom = spacing;
        } else {
            outRect.bottom = EXTRA_SPACING;
        }
    }
} 