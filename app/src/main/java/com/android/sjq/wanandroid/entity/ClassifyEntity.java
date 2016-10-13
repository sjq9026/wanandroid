package com.android.sjq.wanandroid.entity;

/**
 * Created by Administrator on 2016/10/13.
 */

public class ClassifyEntity {
    //分类名称
    private String ClassifyName;
    private String http_url;

    public String getClassifyName() {
        return ClassifyName;
    }

    public void setClassifyName(String classifyName) {
        ClassifyName = classifyName;
    }

    public String getHttp_url() {
        return http_url;
    }

    public void setHttp_url(String http_url) {
        this.http_url = http_url;
    }

    @Override
    public String toString() {
        return "ClassifyEntity{" +
                "ClassifyName='" + ClassifyName + '\'' +
                ", http_url='" + http_url + '\'' +
                '}';
    }
}
