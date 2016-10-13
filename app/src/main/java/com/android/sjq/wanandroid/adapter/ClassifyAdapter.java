package com.android.sjq.wanandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.sjq.wanandroid.R;
import com.android.sjq.wanandroid.entity.ClassifyEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/13.
 */

public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyViewHolder> {
    private Context mContext;
    private ArrayList<ClassifyEntity> mlist;

    public ClassifyAdapter(Context mContext, ArrayList<ClassifyEntity> mlist) {
        this.mContext = mContext;
        this.mlist = mlist;
    }

    @Override
    public ClassifyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ClassifyViewHolder viewHolder = new ClassifyViewHolder(
                LayoutInflater.from(mContext).inflate(R.layout.classify_item_layout, null));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ClassifyViewHolder holder, int position) {
        holder.getClassifyName().setText(mlist.get(position).getClassifyName());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
}
