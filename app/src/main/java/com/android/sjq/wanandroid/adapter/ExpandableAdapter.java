package com.android.sjq.wanandroid.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.android.sjq.wanandroid.R;
import com.android.sjq.wanandroid.entity.LeaderEntity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/18.
 */

public class ExpandableAdapter implements ExpandableListAdapter {
    private ArrayList<LeaderEntity> mList;
    private Context mContext;
    private LayoutInflater inflater;


    public ExpandableAdapter(ArrayList<LeaderEntity> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return mList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mList.get(groupPosition).getSubClassEntities().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mList.get(groupPosition).getSubClassEntities().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.knowledge_lv_item,null);
        TextView tv = (TextView) convertView.findViewById(R.id.name_tv);
        tv.setText(mList.get(groupPosition).getLeaderName());
        tv.setHeight(80);
        tv.setTextColor(Color.parseColor("#542642"));
        tv.setPadding(10,0,0,0);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.knowledge_lv_item,null);
        TextView tv = (TextView) convertView.findViewById(R.id.name_tv);
        tv.setText(mList.get(groupPosition).getSubClassEntities().get(childPosition).getSubClassName());
        tv.setHeight(80);
        tv.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
        tv.setPadding(20,0,0,0);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }
}
