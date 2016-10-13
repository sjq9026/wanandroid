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
        InitClassifiesData();
    }

    private void InitClassifiesData() {
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
                    Log.print("TAG",mClassifies.toString());
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
