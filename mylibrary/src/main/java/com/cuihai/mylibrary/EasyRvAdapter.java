package com.cuihai.mylibrary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * author:  崔海
 * time:    2017/4/18 22:02
 * name:
 * overview:
 * usage:  适用于比较简单的RecyclerView适配器
 */

public abstract class EasyRvAdapter<T> extends RecyclerView.Adapter<EasyRvViewHolder> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public EasyRvAdapter(Context mContext, int mLayoutId, List<T> mDatas) {
        this.mInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.mLayoutId = mLayoutId;
        this.mDatas = mDatas;
    }

    @Override
    public void onBindViewHolder(EasyRvViewHolder holder, final int position) {
        convert(holder, mDatas.get(position), position);
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(view, mDatas.get(position), position);
                }
            });
        }
        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mOnItemLongClickListener.onItemLongClick(view, mDatas.get(position), position);
                    return true;
                }
            });
        }

    }

    public abstract void convert(EasyRvViewHolder easyRvViewHolder, T t, int position);

    @Override
    public EasyRvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(mLayoutId, parent, false);
        return new EasyRvViewHolder(view);
    }


    @Override
    public int getItemCount() {
        if (mDatas == null) {
            mDatas = new ArrayList<T>();
            return 0;
        }
        return mDatas.size();
    }

    /**
     * 清空数据
     */
    public void clearAll() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    /**
     * 设置RV的item点击监听
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }


    public interface OnItemClickListener<T> {
        void onItemClick(View view, T data, int position);
    }

    public interface OnItemLongClickListener<T> {
        void onItemLongClick(View view, T data, int position);
    }
}
