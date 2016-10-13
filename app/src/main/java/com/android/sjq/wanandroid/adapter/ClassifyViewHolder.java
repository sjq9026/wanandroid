package com.android.sjq.wanandroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.sjq.wanandroid.R;

/**
 * Created by Administrator on 2016/10/13.
 */

public class ClassifyViewHolder extends RecyclerView.ViewHolder {
    private TextView classifyName;

    public ClassifyViewHolder(View view) {
        super(view);
        classifyName = (TextView) view.findViewById(R.id.classify_name_tv);
    }

    public TextView getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(TextView classifyName) {
        this.classifyName = classifyName;
    }
}
