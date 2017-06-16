package com.test.ui.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.ui.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MediaFragment extends Fragment {

    int LAYOUT = R.layout.fragment_media;

    public MediaFragment() {
        // Required empty public constructor
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT);
                    switchFragment(1);
                    return true;
                case R.id.navigation_dashboard:
                    Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT);
                    return true;
                case R.id.navigation_notifications:
                    Toast.makeText(getContext(), "3", Toast.LENGTH_SHORT);
                    return true;
            }
            return false;
        }
    };

    void switchFragment(int index) {
        Fragment targetFragment = null;
        String tag = null;
        switch (index) {
            case 1:
                targetFragment = new VideoFragment();
                tag = "video";
                break;
        }

        try {
            FragmentManager fragmentManager = getChildFragmentManager();
            fragmentManager.beginTransaction()
                    //.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .replace(R.id.container, targetFragment, tag)
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(LAYOUT, container, false);

        BottomNavigationView navigation = (BottomNavigationView) view.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        switchFragment(1);
        return view;
    }
}
