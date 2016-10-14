package com.android.sjq.wanandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.sjq.wanandroid.R;
import com.android.sjq.wanandroid.entity.RecentlyBlogInfoEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/14.
 */

public class RecentlyBlogAdapter extends RecyclerView.Adapter<RecentlyBlogViewHolder> {
    private Context mContext;
    private ArrayList<RecentlyBlogInfoEntity> mList;
    private LayoutInflater mInflater;
    private OnItemClickListener listener;

    public RecentlyBlogAdapter(Context context, ArrayList<RecentlyBlogInfoEntity> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecentlyBlogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecentlyBlogViewHolder(mContext,mInflater.inflate(R.layout.recently_blog_item_layout, null));
    }

    @Override
    public void onBindViewHolder(final RecentlyBlogViewHolder holder, final int position) {
        if (position == 0) {
            holder.getBlog_name_tv().setPadding(20, 20, 0, 0);
            holder.getClassify_name_tv().setPadding(20, 20, 0, 0);
        }
        holder.getLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onBlogNameClickListener(holder.getLayoutPosition());
            }
        });
        holder.getClassify_name_tv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClassifyNameClickListener(holder.getLayoutPosition());
            }
        });
        RecentlyBlogInfoEntity entity = mList.get(position);
        holder.getBlog_name_tv().setText(entity.getBlogname());
        holder.getClassify_name_tv().setText(entity.getClassify());
        holder.getAuthor_name_tv().setText(entity.getAuthor());
        holder.getSource_tv().setText(entity.getSource());
        holder.getDate_tv().setText(entity.getDate());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
