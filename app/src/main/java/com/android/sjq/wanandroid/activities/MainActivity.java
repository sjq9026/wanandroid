package com.android.sjq.wanandroid.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.sjq.wanandroid.Contacts;
import com.android.sjq.wanandroid.R;
import com.android.sjq.wanandroid.adapter.ClassifyAdapter;
import com.android.sjq.wanandroid.entity.ClassifyEntity;
import com.android.sjq.wanandroid.entity.RecentlyBlogInfoEntity;
import com.android.sjq.wanandroid.tool.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView classify_rv;
    private ArrayList<ClassifyEntity> mClassifies;
    private ArrayList<RecentlyBlogInfoEntity> mRecentlyBlogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    /**
     *
     */
    private void initData() {
        //获取分类数据
        initClassifiesData();
        //获取最近热门信息数据
        initRecentlyData();
    }

    /**
     * 获取最近热门信息
     */
    private void initRecentlyData() {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {
                Document document;
                mRecentlyBlogs = new ArrayList<>();
                try {
                    document = Jsoup.connect(Contacts.BASE_URL).get();
                    RecentlyBlogInfoEntity recentlyBlogInfoEntity = null;
                    Elements elements = document.getElementsByClass("item_div");
                    for (Element e : elements) {
                        Elements e1 = e.getElementsByClass("article_div");
                        recentlyBlogInfoEntity = new RecentlyBlogInfoEntity();
//                        System.out.println("\n************************************************************************************************\n");
//                        System.out.println("博客名称：" + e1.select("a").get(0).text());
//                        System.out.println("地址：" + e1.select("a").get(0).attr("href"));
//                        System.out.println(e1.select("span").get(0).text());
//                        System.out.println(e1.select("span").get(1).text());
//                        System.out.println(e1.select("span").get(2).text());
//                        System.out.println("分类列表：" + Contacts.BASE_URL + e1.select("span").get(2).select("a").attr("href"));
//                        System.out.println("时间：" + e1.select("span").get(3).text());
                        recentlyBlogInfoEntity.setBlogname(e1.select("a").get(0).text());
                        recentlyBlogInfoEntity.setBlogaddress(e1.select("a").get(0).attr("href"));
                        recentlyBlogInfoEntity.setAuthor(e1.select("span").get(0).text());
                        recentlyBlogInfoEntity.setSource(e1.select("span").get(1).text());
                        recentlyBlogInfoEntity.setClassify(e1.select("span").get(2).text());
                        recentlyBlogInfoEntity.setClassifyaddress(Contacts.BASE_URL + e1.select("span").get(2).select("a").attr("href"));
                        recentlyBlogInfoEntity.setDate(e1.select("span").get(3).text());
                        mRecentlyBlogs.add(recentlyBlogInfoEntity);
                    }

                    Log.print("Blog",mRecentlyBlogs.toString());
                    Log.print("TAG", mClassifies.toString());
                } catch (IOException e) {
                    document = null;
                    e.printStackTrace();
                }
                if (mClassifies != null && mClassifies.size() > 0) {
                    return "OK";
                } else {
                    return "ERR";
                }

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        }.execute();
    }

    private void initClassifiesData() {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {
                Document document;
                mClassifies = new ArrayList<>();
                try {
                    document = Jsoup.connect(Contacts.BASE_URL).get();
                    Elements classifiesEle = document.getElementsByClass("menu").select("li");
                    ClassifyEntity classifyEntity;
                    for (Element element : classifiesEle) {
                        classifyEntity = new ClassifyEntity();
                        classifyEntity.setClassifyName(element.select("li").text());
                        classifyEntity.setHttp_url(Contacts.BASE_URL + element.select("li").select("a").attr("href"));
                        mClassifies.add(classifyEntity);
                    }
                    Log.print("TAG", mClassifies.toString());
                } catch (IOException e) {
                    document = null;
                    e.printStackTrace();
                }
                if (mClassifies != null && mClassifies.size() > 0) {
                    return "OK";
                } else {
                    return "ERR";
                }

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                ClassifyAdapter adapter = new ClassifyAdapter(MainActivity.this, mClassifies);
                classify_rv.setAdapter(adapter);
            }
        }.execute();
    }

    private void initView() {
        classify_rv = (RecyclerView) findViewById(R.id.classify_rv);
        classify_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }
}
