package com.android.sjq.wanandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.sjq.wanandroid.R;
import com.android.sjq.wanandroid.entity.RecentlyBlogInfoEntity;
import com.android.sjq.wanandroid.tool.Log;

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
        this.mContext = context;
        this.mList = list;
        this.mInflater = LayoutInflater.from(mContext);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecentlyBlogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        android.util.Log.i("RecyclerView", "onCreateViewHolder: ");
        RecentlyBlogViewHolder viewholder = new RecentlyBlogViewHolder(mContext,
                mInflater.inflate(R.layout.recently_blog_item_layout, null));
        return viewholder;
    }

    @Override
    public void onBindViewHolder(RecentlyBlogViewHolder holder, final int position) {
        android.util.Log.i("RecyclerView", "onBindViewHolder: ");
        if (position == 0) {
            holder.blog_name_tv.setPadding(20, 20, 0, 0);
            holder.classify_name_tv.setPadding(20, 20, 0, 0);
        }
        final RecentlyBlogViewHolder finalHoler = holder;
        RecentlyBlogInfoEntity entity = mList.get(position);
        holder.blog_name_tv.setText(entity.getBlogname());
        Log.print("blogName", entity.getBlogname());
        if (entity.getClassify() == null) {
            holder.classify_name_tv.setVisibility(View.GONE);
        }

        holder.author_name_tv.setText(entity.getAuthor());
        holder.source_tv.setText(entity.getSource());
        holder.date_tv.setText(entity.getDate());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onBlogNameClickListener(finalHoler.getLayoutPosition());
                }
            }
        });
        holder.classify_name_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClassifyNameClickListener(finalHoler.getLayoutPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.print("ItemCount","ItemCount----->"+mList.size()+"");
        return mList.size();
    }
}
