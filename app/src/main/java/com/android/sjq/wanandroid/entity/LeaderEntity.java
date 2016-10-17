package com.android.sjq.wanandroid.entity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/17.
 */

public class LeaderEntity {
    private String LeaderName;
    private ArrayList<SubClassEntity> subClassEntities;

    public String getLeaderName() {
        return LeaderName;
    }

    public void setLeaderName(String leaderName) {
        LeaderName = leaderName;
    }

    public ArrayList<SubClassEntity> getSubClassEntities() {
        return subClassEntities;
    }

    public void setSubClassEntities(ArrayList<SubClassEntity> subClassEntities) {
        this.subClassEntities = subClassEntities;
    }
}
