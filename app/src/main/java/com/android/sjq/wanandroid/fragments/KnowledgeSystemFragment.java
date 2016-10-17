package com.android.sjq.wanandroid.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.sjq.wanandroid.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link KnowledgeSystemFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link KnowledgeSystemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KnowledgeSystemFragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_knowledge_system, container, false);
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
