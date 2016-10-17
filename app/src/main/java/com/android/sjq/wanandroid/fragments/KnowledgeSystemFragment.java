package com.android.sjq.wanandroid.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.android.sjq.wanandroid.Contacts;
import com.android.sjq.wanandroid.R;
import com.android.sjq.wanandroid.entity.LeaderEntity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

//知识体系Fragment
public class KnowledgeSystemFragment extends Fragment {

    private ExpandableListView expandable_lv;
    private ListView know_lv;
    private ArrayList<LeaderEntity> leaderEntities;

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
                    document = Jsoup.connect(Contacts.KNOW_SYSTEM).get();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        }.execute();
    }

    /**
     * 初始化界面
     */
    private void initView(View view) {
        expandable_lv = (ExpandableListView) view.findViewById(R.id.expandable_lv);
        know_lv = (ListView) view.findViewById(R.id.know_lv);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
