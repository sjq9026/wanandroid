package com.android.sjq.wanandroid.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Administrator on 2016/10/17.
 */

public interface OnClassifyItemClickListener<VH extends RecyclerView.ViewHolder> {
    void onItemClick(int position, VH viewHolder);
}
