package com.android.sjq.wanandroid.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.android.sjq.wanandroid.Contacts;
import com.android.sjq.wanandroid.R;
import com.android.sjq.wanandroid.adapter.ClassifyAdapter;
import com.android.sjq.wanandroid.adapter.ClassifyViewHolder;
import com.android.sjq.wanandroid.adapter.OnClassifyItemClickListener;
import com.android.sjq.wanandroid.entity.ClassifyEntity;
import com.android.sjq.wanandroid.fragments.FrameworkFragment;
import com.android.sjq.wanandroid.fragments.HomeFragment;
import com.android.sjq.wanandroid.fragments.KnowledgeSystemFragment;
import com.android.sjq.wanandroid.fragments.NewTechnologyFragment;
import com.android.sjq.wanandroid.fragments.OpenSourceProjectFragment;
import com.android.sjq.wanandroid.fragments.PopularTopicsFragment;
import com.android.sjq.wanandroid.tool.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClassifyItemClickListener<ClassifyViewHolder> {
    private RecyclerView classify_rv;
    private ArrayList<ClassifyEntity> mClassifies;
    private HomeFragment homeFragment;
    private FrameworkFragment frameworkFragment;
    private KnowledgeSystemFragment knowledgeSystemFragment;
    private NewTechnologyFragment newTechnologyFragment;
    private PopularTopicsFragment popularTopicsFragment;
    private OpenSourceProjectFragment openSourceProjectFragment;
    private FrameLayout frameLayout;
    private String[] flags = {"HomeFragment", "knowledgeSystemFragment", "popularTopicsFragment", "frameworkFragment", "newTechnologyFragment", "openSourceProjectFragment"};
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        if (savedInstanceState == null) {
            homeFragment = HomeFragment.newInstance();
            if (!homeFragment.isAdded()) {
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.add(R.id.content_layout, homeFragment, flags[0]);
                transaction.commit();
            }

            currentFragment = homeFragment;
        }
    }

    /**
     *
     */
    private void initData() {
        //获取分类数据
        initClassifiesData();

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
                adapter.setOnItemClickListener(MainActivity.this);
                classify_rv.setAdapter(adapter);
            }
        }.execute();
    }

    private void initView() {
        classify_rv = (RecyclerView) findViewById(R.id.classify_rv);
        classify_rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        frameLayout = (FrameLayout) findViewById(R.id.content_layout);
    }


    @Override
    public void onItemClick(int position, final ClassifyViewHolder holder) {
        classify_rv.scrollToPosition(position);
        /** //获取控件的位置
         final int location[] = new int[2];
         holder.getClassifyName().getLocationInWindow(location);
         //获取屏幕宽度
         DisplayMetrics metrics = getResources().getDisplayMetrics();
         final int width = metrics.widthPixels;
         int height = metrics.heightPixels;
         //获取TextView的宽度
         //计算滚动的位置
         int x = location[0] - width / 2;
         Log.print("X", x + "");
         classify_rv.scrollBy(x, location[1]);*/
        android.support.v4.app.FragmentManager manager = getSupportFragmentManager();
        switch (mClassifies.get(position).getClassifyName()) {
            case Contacts.HOME:
                if (homeFragment == null) {
                    homeFragment = HomeFragment.newInstance();
                }
                FragmentTransaction tran = manager.beginTransaction();

                tran.hide(currentFragment);
                manager.executePendingTransactions();
                if (!homeFragment.isAdded()) {
                    tran.add(R.id.content_layout, homeFragment, flags[0]);
                }
                tran.show(homeFragment);
                currentFragment = homeFragment;
                tran.commit();
                Log.print("Click", Contacts.HOME);
                break;
            case Contacts.KNOWLEDGE_SYSTEM:
                if (knowledgeSystemFragment == null) {
                    knowledgeSystemFragment = KnowledgeSystemFragment.newInstance();
                }
                FragmentTransaction tran1 = manager.beginTransaction();
                tran1.hide(currentFragment);
                manager.executePendingTransactions();
                if (!knowledgeSystemFragment.isAdded()) {
                    tran1.add(R.id.content_layout, knowledgeSystemFragment, flags[1]);
                }
                tran1.show(knowledgeSystemFragment);
                currentFragment = knowledgeSystemFragment;
                tran1.commit();
                Log.print("Click", Contacts.KNOWLEDGE_SYSTEM);
                break;
            case Contacts.POPULAR_TOPICS:
                if (popularTopicsFragment == null) {
                    popularTopicsFragment = PopularTopicsFragment.newInstance();
                }
                FragmentTransaction transaction2 = manager.beginTransaction();
                transaction2.hide(currentFragment);
                manager.executePendingTransactions();
                if (!popularTopicsFragment.isAdded()) {
                    transaction2.add(R.id.content_layout, popularTopicsFragment, flags[2]);
                }
                currentFragment = popularTopicsFragment;
                transaction2.show(popularTopicsFragment);
                transaction2.commit();
                Log.print("Click", Contacts.POPULAR_TOPICS);
                break;
            case Contacts.COMMON_FRAMEWORK_FOR_PROJECTS:
                if (frameworkFragment == null) {
                    frameworkFragment = FrameworkFragment.newInstance();
                }
                FragmentTransaction transaction3 = manager.beginTransaction();
                transaction3.hide(currentFragment);
                if (!frameworkFragment.isAdded()) {
                    transaction3.add(R.id.content_layout, frameworkFragment, flags[3]);
                }
                manager.executePendingTransactions();
                currentFragment = frameworkFragment;
                transaction3.show(frameworkFragment);
                transaction3.commit();
                Log.print("Click", Contacts.COMMON_FRAMEWORK_FOR_PROJECTS);
                break;
            case Contacts.HIGH_TECH:
                if (newTechnologyFragment == null) {
                    newTechnologyFragment = NewTechnologyFragment.newInstance();
                }
                FragmentTransaction transaction4 = manager.beginTransaction();
                transaction4.hide(currentFragment);
                if (!newTechnologyFragment.isAdded()) {
                    transaction4.add(R.id.content_layout, newTechnologyFragment, flags[4]);
                }
                manager.executePendingTransactions();
                currentFragment = newTechnologyFragment;
                transaction4.show(newTechnologyFragment);
                transaction4.commit();
                Log.print("Click", Contacts.HIGH_TECH);
                break;
            case Contacts.OPEN_SOURCE_PROJECT_CATEGORY:
                if (openSourceProjectFragment == null) {
                    openSourceProjectFragment = openSourceProjectFragment.newInstance();
                }
                FragmentTransaction transaction5 = manager.beginTransaction();
                transaction5.hide(currentFragment);
                if (!openSourceProjectFragment.isAdded()) {
                    transaction5.add(R.id.content_layout, openSourceProjectFragment, flags[5]);
                }
                manager.executePendingTransactions();
                currentFragment = openSourceProjectFragment;
                transaction5.show(openSourceProjectFragment);
                transaction5.commit();
                Log.print("Click", Contacts.OPEN_SOURCE_PROJECT_CATEGORY);
                break;
        }


    }
}
