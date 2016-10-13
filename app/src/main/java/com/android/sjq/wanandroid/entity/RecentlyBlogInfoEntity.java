package com.android.sjq.wanandroid.entity;

/**
 * Created by Administrator on 2016/10/13.
 * 最近博文信息实体类
 */

public class RecentlyBlogInfoEntity {
    //博客名称
    private String blogname;
    //博客地址
    private String blogaddress;
    //作者
    private String author;
    //来源
    private String source;
    //分类
    private String classify;
    //分类列表
    private String classifyaddress;
    //时间
    private String date;

    public String getBlogname() {
        return blogname;
    }

    public void setBlogname(String blogname) {
        this.blogname = blogname;
    }

    public String getBlogaddress() {
        return blogaddress;
    }

    public void setBlogaddress(String blogaddress) {
        this.blogaddress = blogaddress;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getClassifyaddress() {
        return classifyaddress;
    }

    public void setClassifyaddress(String classifyaddress) {
        this.classifyaddress = classifyaddress;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RecentlyBlogInfoEntity{" +
                "blogname='" + blogname + '\'' +
                ", blogaddress='" + blogaddress + '\'' +
                ", author='" + author + '\'' +
                ", source='" + source + '\'' +
                ", classify='" + classify + '\'' +
                ", classifyaddress='" + classifyaddress + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
