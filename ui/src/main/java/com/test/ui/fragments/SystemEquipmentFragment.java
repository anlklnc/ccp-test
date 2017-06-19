package com.test.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.ui.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemEquipmentFragment extends Fragment {

    final int LAYOUT = R.layout.fragment_system_equipment;

    public SystemEquipmentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(LAYOUT, container, false);
        return view;
    }
}