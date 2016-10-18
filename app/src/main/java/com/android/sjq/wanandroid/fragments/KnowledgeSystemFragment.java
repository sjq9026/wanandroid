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
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.android.sjq.wanandroid.Contacts;
import com.android.sjq.wanandroid.R;
import com.android.sjq.wanandroid.activities.BlogDetailActivity;
import com.android.sjq.wanandroid.adapter.ExpandableAdapter;
import com.android.sjq.wanandroid.adapter.OnItemClickListener;
import com.android.sjq.wanandroid.adapter.RecentlyBlogAdapter;
import com.android.sjq.wanandroid.entity.LeaderEntity;
import com.android.sjq.wanandroid.entity.RecentlyBlogInfoEntity;
import com.android.sjq.wanandroid.entity.SubClassEntity;
import com.android.sjq.wanandroid.tool.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

//知识体系Fragment
public class KnowledgeSystemFragment extends Fragment implements ExpandableListView.OnChildClickListener ,OnItemClickListener{

    private ExpandableListView expandable_lv;
    private RecyclerView know_rl;
    private ArrayList<LeaderEntity> leaderEntities;
    private ArrayList<RecentlyBlogInfoEntity> mRecentlyBlogs;

    public KnowledgeSystemFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static KnowledgeSystemFragment newInstance() {
        KnowledgeSystemFragment fragment = new KnowledgeSystemFragment();
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
        View view = inflater.inflate(R.layout.fragment_knowledge_system, container, false);
        initView(view);
        initData();
        return view;
    }

    /**
     * 初始化data
     */
    private void initData() {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {
                Document document;
                try {
                    //一级菜单
                    leaderEntities = new ArrayList<LeaderEntity>();
                    //二级菜单
                    ArrayList<SubClassEntity> subClassEntities = null;
                    //二级菜单对象
                    SubClassEntity subClassEntity = null;
                    document = Jsoup.connect(Contacts.KNOW_SYSTEM_BASE_URL).get();
                    Elements elements1 = document.getElementsByClass("left");
                    for (int i = 0; i < elements1.size(); i++) {
                        Element es = elements1.get(i);
                        Elements alles = es.getElementsByTag("ul");
                        Element e = alles.get(0);
                        //获取所有的a标签
                        Elements subUls = e.getElementsByTag("a");
                        LeaderEntity entity = null;
                        int leaderSum = -1;
                        //遍历a标签
                        for (int j = 0; j < subUls.size(); j++) {
                            Element ule = subUls.get(j);
                            //如果当前a标签是一级标签
                            if (ule.attr("class").equals("leader")) {
                                entity = new LeaderEntity();
                                entity.setLeaderName(ule.text());
                                leaderEntities.add(entity);
                                subClassEntities = null;
                                leaderSum++;
                                //  System.out.println(ule.text());
                                //如果是二级标签，创建二级标签集合，将当前一级标签下所有二级标签添加到集合中
                            } else {
                                if (subClassEntities == null) {
                                    subClassEntities = new ArrayList<SubClassEntity>();
                                }
                                subClassEntity = new SubClassEntity();
                                subClassEntity.setSubClassName(ule.text());
                                subClassEntity.setSubClassUrl(Contacts.BASE_URL + ule.attr("href"));
                                subClassEntities.add(subClassEntity);
                                entity.setSubClassEntities(subClassEntities);
                                // Log.print("sub",leaderEntities.get(leaderSum).toString());
                                //  System.out.println("      " + ule.text());
                            }
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.print("sub", leaderEntities.size() + "");
                return "OK";
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s.equals("OK")) {
                    ExpandableAdapter adapter = new ExpandableAdapter(leaderEntities, KnowledgeSystemFragment.this.getActivity());
                    expandable_lv.setAdapter(adapter);
                    //设置第一项为展开
                    expandable_lv.expandGroup(0);
                    //获取当前分类的二级列表
                    getBlogList(leaderEntities.get(0).getSubClassEntities().get(0));
                } else {
                    Toast.makeText(KnowledgeSystemFragment.this.getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }


    /**
     * 初始化界面
     */
    private void initView(View view) {
        expandable_lv = (ExpandableListView) view.findViewById(R.id.expandable_lv);
        expandable_lv.setGroupIndicator(null);
        expandable_lv.setOnChildClickListener(this);
        know_rl = (RecyclerView) view.findViewById(R.id.know_rl);
        know_rl.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    /**
     * 获取当前二级分类下的列表
     *
     * @param subClassEntity
     */
    private void getBlogList(final SubClassEntity subClassEntity) {
        new AsyncTask<String, Integer, String>() {
            String url = Contacts.BASE_URL + subClassEntity.getSubClassUrl();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.print("url", url);
            }

            @Override
            protected String doInBackground(String... params) {
                Document document = null;
                mRecentlyBlogs = new ArrayList<RecentlyBlogInfoEntity>();
                try {
                    document = Jsoup.connect(url).get();
                    Elements es = document.getElementsByClass("article_div");
                    RecentlyBlogInfoEntity entity = null;
                    for (int i = 0; i < es.size(); i++) {
                        Element e = es.get(i);
                        entity = new RecentlyBlogInfoEntity();
//                        System.out.println(e.select("a").text() + ":" + e.select("a").attr("href"));
//                        System.out.println(e.select("span").get(0).text());
//                        System.out.println(e.select("span").get(1).text());
//                        System.out.println(e.select("span").get(2).text() + "\n");
                        entity.setBlogname(e.select("a").text());
                        entity.setBlogaddress(e.select("a").attr("href"));
                        entity.setAuthor(e.select("span").get(0).text());
                        entity.setSource(e.select("span").get(1).text());
                        entity.setDate(e.select("span").get(2).text());
                        entity.setClassify(null);
                        entity.setClassifyaddress(null);
                        mRecentlyBlogs.add(entity);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.print("data", mRecentlyBlogs.toString());
                return "OK";
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                RecentlyBlogAdapter adapter =
                        new RecentlyBlogAdapter(getActivity(),mRecentlyBlogs);
                adapter.setOnItemClickListener(KnowledgeSystemFragment.this);
                know_rl.setAdapter(adapter);
            }
        }.execute();
    }






    @Override

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        getBlogList(leaderEntities.get(groupPosition).getSubClassEntities().get(childPosition));
        return true;
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

    }
}
