package com.steelcrow.androchat;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.steelcrow.androchat.navigation.NavigationActivity;

public class FragmentAbout extends Fragment {

    private String title;

    public static FragmentAbout newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        FragmentAbout fragmentAbout = new FragmentAbout();
        fragmentAbout.setArguments(bundle);
        return fragmentAbout;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString("title");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        NavigationActivity activity = (NavigationActivity) getActivity();
        activity.setActionBarTitle(title);
        return view;
    }
}
