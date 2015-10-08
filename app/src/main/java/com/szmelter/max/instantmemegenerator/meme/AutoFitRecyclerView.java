package com.szmelter.max.instantmemegenerator.meme;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class AutoFitRecyclerView extends RecyclerView {

    int columnWidth;
    GridLayoutManager layoutManager;

    public AutoFitRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (attrs != null) {
            int[] attrsArray = {android.R.attr.columnWidth};
            TypedArray array = context.obtainStyledAttributes(attrs, attrsArray);
            this.columnWidth = array.getDimensionPixelSize(0, -1);
            array.recycle();
        }

        this.layoutManager = new GridLayoutManager(getContext(), 1);
        setLayoutManager(this.layoutManager);

    }

    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
        if (this.columnWidth > 0) {
            int spanCount = Math.max(1, getMeasuredWidth() / this.columnWidth);
            this.layoutManager.setSpanCount(spanCount);
        }
    }
}