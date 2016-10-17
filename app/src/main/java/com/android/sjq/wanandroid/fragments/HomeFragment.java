package com.android.sjq.wanandroid.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.sjq.wanandroid.Contacts;
import com.android.sjq.wanandroid.R;
import com.android.sjq.wanandroid.activities.BlogDetailActivity;
import com.android.sjq.wanandroid.adapter.OnItemClickListener;
import com.android.sjq.wanandroid.adapter.RecentlyBlogAdapter;
import com.android.sjq.wanandroid.entity.RecentlyBlogInfoEntity;
import com.android.sjq.wanandroid.tool.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
//首页Fragment
public class HomeFragment extends Fragment implements OnItemClickListener {
    private RecyclerView recently_blog_rv;
    private ArrayList<RecentlyBlogInfoEntity> mRecentlyBlogs;

    public HomeFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        //获取最近热门信息数据
        initRecentlyData();
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private void initView(View view) {
        recently_blog_rv = (RecyclerView) view.findViewById(R.id.recently_blog_rv);
        recently_blog_rv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
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
                        recentlyBlogInfoEntity.setBlogname(e1.select("a").get(0).text().toString());
                        recentlyBlogInfoEntity.setBlogaddress(e1.select("a").get(0).attr("href").toString());
                        recentlyBlogInfoEntity.setAuthor(e1.select("span").get(0).text().toString());
                        recentlyBlogInfoEntity.setSource(e1.select("span").get(1).text().toString());
                        recentlyBlogInfoEntity.setClassify(e1.select("span").get(2).text().toString());
                        recentlyBlogInfoEntity.setClassifyaddress(Contacts.BASE_URL + e1.select("span").get(2).select("a").attr("href").toString());
                        recentlyBlogInfoEntity.setDate(e1.select("span").get(3).text().toString());
                        mRecentlyBlogs.add(recentlyBlogInfoEntity);
                    }

//                    Log.print("Blog",mRecentlyBlogs.toString());
//                    Log.print("TAG", mClassifies.toString());
                } catch (IOException e) {
                    document = null;
                    e.printStackTrace();
                }
                if (mRecentlyBlogs != null && mRecentlyBlogs.size() > 0) {
                    return "OK";
                } else {
                    return "ERR";
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                RecentlyBlogAdapter adapter = new RecentlyBlogAdapter(getActivity(), mRecentlyBlogs);
                adapter.setOnItemClickListener(HomeFragment.this);
                recently_blog_rv.setAdapter(adapter);
            }
        }.execute();
    }

    @Override
    public void onBlogNameClickListener(int position) {
        Log.print("log", "onBlogNameClickListener----->" + position + "");
        Intent intent = new Intent(getActivity(), BlogDetailActivity.class);
        intent.putExtra("url", mRecentlyBlogs.get(position).getBlogaddress());
        Log.print("url", mRecentlyBlogs.get(position).getBlogaddress());
        startActivity(intent);
    }

    @Override
    public void onClassifyNameClickListener(int position) {
        Log.print("log", "onClassifyNameClickListener----->" + position + "");
        Intent intent = new Intent(getActivity(), BlogDetailActivity.class);
        intent.putExtra("url", mRecentlyBlogs.get(position).getClassifyaddress());
        Log.print("url", mRecentlyBlogs.get(position).getBlogaddress());
        startActivity(intent);
    }
}
