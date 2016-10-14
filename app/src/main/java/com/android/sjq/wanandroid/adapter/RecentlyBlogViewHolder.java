package com.android.sjq.wanandroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.sjq.wanandroid.R;

/**
 * Created by Administrator on 2016/10/14.
 */

public class RecentlyBlogViewHolder extends RecyclerView.ViewHolder {
    private TextView blog_name_tv;
    private TextView classify_name_tv;
    private TextView author_name_tv;
    private TextView source_tv;
    private TextView date_tv;
    private LinearLayout layout;


    public RecentlyBlogViewHolder(Context context,View itemView) {
        super(itemView);
        blog_name_tv = (TextView) itemView.findViewById(R.id.blog_name_tv);
        classify_name_tv = (TextView) itemView.findViewById(R.id.classify_name_tv);
        author_name_tv = (TextView) itemView.findViewById(R.id.author_name_tv);
        source_tv = (TextView) itemView.findViewById(R.id.source_tv);
        date_tv = (TextView) itemView.findViewById(R.id.date_tv);
        layout = (LinearLayout)itemView.findViewById(R.id.content_layout);
    }

    public TextView getBlog_name_tv() {
        return blog_name_tv;
    }

    public void setBlog_name_tv(TextView blog_name_tv) {
        this.blog_name_tv = blog_name_tv;
    }

    public TextView getClassify_name_tv() {
        return classify_name_tv;
    }

    public void setClassify_name_tv(TextView classify_name_tv) {
        this.classify_name_tv = classify_name_tv;
    }

    public TextView getAuthor_name_tv() {
        return author_name_tv;
    }

    public void setAuthor_name_tv(TextView author_name_tv) {
        this.author_name_tv = author_name_tv;
    }

    public TextView getSource_tv() {
        return source_tv;
    }

    public void setSource_tv(TextView source_tv) {
        this.source_tv = source_tv;
    }

    public TextView getDate_tv() {
        return date_tv;
    }

    public void setDate_tv(TextView date_tv) {
        this.date_tv = date_tv;
    }

    public LinearLayout getLayout() {
        return layout;
    }

    public void setLayout(LinearLayout layout) {
        this.layout = layout;
    }
}
