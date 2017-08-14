package com.test.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.test.ui.ButtonListener;
import com.test.ui.R;
import com.test.ui.ViewUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class KifeFragment extends Fragment {

    static final int LAYOUT = R.layout.fragment_kife;

    public KifeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(LAYOUT, container, false);

        ImageView power = (ImageView)view.findViewById(R.id.power);
        power.setImageResource(R.drawable.graph_power_button);
        ViewUtil.addButtonAnimation(power, new ButtonListener() {
            @Override
            public void onClick(boolean enable) {
                Log.i("!!!", "onClick: " + enable);
            }
        }, '0');
        return view;
    }
}
