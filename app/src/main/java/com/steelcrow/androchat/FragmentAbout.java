package com.steelcrow.androchat;

import android.support.annotation.Nullable;
import android.support.transition.TransitionManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

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
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        //animation
        final ImageButton personView = (ImageButton) view.findViewById(R.id.imageView_about);
        personView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newWidth = getResources().getDimensionPixelSize(R.dimen.person_about_max_width);
                int newHeight = getResources().getDimensionPixelSize(R.dimen.person_about_max_height);
                TransitionManager.beginDelayedTransition(container);
                ViewGroup.LayoutParams params = personView.getLayoutParams();
                params.width = newWidth;
                params.height = newHeight;
                personView.setLayoutParams(params);
            }
        });



        NavigationActivity activity = (NavigationActivity) getActivity();
        activity.getSupportActionBar().setTitle(title);
        return view;
    }
}
