package com.test.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.test.ui.FlightInfoItem;
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
        LinearLayout layout1 = (LinearLayout)view.findViewById(R.id.lin_equipment_list_1);
        LinearLayout layout2 = (LinearLayout)view.findViewById(R.id.lin_equipment_list_2);

        Context c = getContext();

        FlightInfoItem item;
        item = new FlightInfoItem(c, 2, "System Mode", "Restricted Mode");
        layout1.addView(item);
        item = new FlightInfoItem(c, 2, "GSM Carrier Power", "On");
        layout1.addView(item);
        item = new FlightInfoItem(c, 2, "GSM Connection Status", "On");
        layout1.addView(item);
        item = new FlightInfoItem(c, 2, "Brake", "Set");
        layout1.addView(item);
        item = new FlightInfoItem(c, 2, "Air/Ground", "Ground");
        layout1.addView(item);
        item = new FlightInfoItem(c, 2, "Public Announcement", "Off");
        layout1.addView(item);
        item = new FlightInfoItem(c, 2, "Flight Mode", "3g Activated");
        layout2.addView(item);
        item = new FlightInfoItem(c, 2, "Flight State", "Operational");
        layout2.addView(item);
        item = new FlightInfoItem(c, 2, "License Server", "On");
        layout2.addView(item);
        item = new FlightInfoItem(c, 2, "Enable 2 Discrete", "Off");
        layout2.addView(item);
        item = new FlightInfoItem(c, 2, "Enable 3 Discrete", "Off");
        layout2.addView(item);
        item = new FlightInfoItem(c, 2, "Enable 4 Discrete", "Off");
        layout2.addView(item);

        return view;
    }
}