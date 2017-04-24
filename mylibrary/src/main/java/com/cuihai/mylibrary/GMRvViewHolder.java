package com.cuihai.mylibrary;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * author:  崔海
 * time:    2017/4/18 22:03
 * name:
 * overview:
 * usage:
 */

public class GMRvViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;

    public GMRvViewHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
        mConvertView = itemView;
    }

    /**
     * 通过viewId获取控件
     */

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
}
