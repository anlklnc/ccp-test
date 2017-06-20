package com.test.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.test.ui.EquipmentItem;
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
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.lin_content);
        EquipmentItem item1 = new EquipmentItem(getContext(), "Web Server", "Running");
        layout.addView(item1);
        EquipmentItem item2 = new EquipmentItem(getContext(), "PA Server", "Running");
        layout.addView(item2);
        EquipmentItem item3 = new EquipmentItem(getContext(), "Map Server", "Running");
        layout.addView(item3);
        EquipmentItem item4 = new EquipmentItem(getContext(), "Discrete Reader", "Running");
        layout.addView(item4);
        EquipmentItem item5 = new EquipmentItem(getContext(), "Log Sender", "Running");
        layout.addView(item5);
        EquipmentItem item6 = new EquipmentItem(getContext(), "ARINC 429", "Running");
        layout.addView(item6);
        return view;
    }
}