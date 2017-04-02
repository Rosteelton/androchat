package com.steelcrow.androchat.navigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.steelcrow.androchat.R;

public class EmptyFragment extends Fragment {

    private String title;

    public static EmptyFragment newInstance(String title) {
        EmptyFragment fragment = new EmptyFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_empty, container, false);
        NavigationActivity activity = (NavigationActivity) getActivity();
        activity.getSupportActionBar().setTitle(title);
        return view;
    }
}
