package com.android.sjq.wanandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.sjq.wanandroid.R;
import com.android.sjq.wanandroid.entity.ClassifyEntity;
import com.android.sjq.wanandroid.tool.Log;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/13.
 */

public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyViewHolder> {
    private Context mContext;
    private ArrayList<ClassifyEntity> mlist;
    private OnClassifyItemClickListener listener;

    public ClassifyAdapter(Context mContext, ArrayList<ClassifyEntity> mlist) {
        this.mContext = mContext;
        this.mlist = mlist;
    }

    @Override
    public ClassifyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ClassifyViewHolder viewHolder = new ClassifyViewHolder(mContext,
                LayoutInflater.from(mContext).inflate(R.layout.classify_item_layout, null));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ClassifyViewHolder holder, int position) {
        holder.getClassifyName().setText(mlist.get(position).getClassifyName());
        holder.getClassifyName().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(holder.getLayoutPosition(),holder);
            }
        });
    }

    @Override
    public int getItemCount() {

        return mlist.size();
    }

    public void setOnItemClickListener(OnClassifyItemClickListener listener) {
        this.listener = listener;
    }
}
