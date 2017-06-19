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

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SystemInformationFragment extends Fragment {

    @Bind(R.id.lin_left)LinearLayout layout1;
    @Bind(R.id.lin_right)LinearLayout layout2;

    public SystemInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_system_information, container, false);
        ButterKnife.bind(this, view);
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
        layout1.addView(item);
        item = new FlightInfoItem(c, 2, "Flight State", "Operational");
        layout1.addView(item);
        item = new FlightInfoItem(c, 2, "License Server", "On");
        layout1.addView(item);
        item = new FlightInfoItem(c, 2, "Enable 2 Discrete", "Off");
        layout1.addView(item);

        item = new FlightInfoItem(c, 2, "Wiring Configuration", "BOEING 3 WAPS");
        layout2.addView(item);
        item = new FlightInfoItem(c, 2, "System State", "OPERATIONAL");
        layout2.addView(item);
        item = new FlightInfoItem(c, 2, "System Mode", "3G_ACTIVATED");
        layout2.addView(item);
        item = new FlightInfoItem(c, 2, "Systme Phase", "PARK_GATE");
        layout2.addView(item);
        item = new FlightInfoItem(c, 2, "Server Part Number", "CM0001-901");
        layout2.addView(item);
        item = new FlightInfoItem(c, 2, "CCP Part Number", "CM0001-901");
        layout2.addView(item);
        item = new FlightInfoItem(c, 2, "Media Content Version", "June2017");
        layout2.addView(item);
        item = new FlightInfoItem(c, 2, "System CSCI Version", "IFE_SYSTEM_BUILD_3.1");
        layout2.addView(item);
        item = new FlightInfoItem(c, 2, "Content CSCI Version", "IFE_CONTENT_BUILD_3.1");
        layout2.addView(item);
        item = new FlightInfoItem(c, 2, "CS CSCI Version", "IFE_CS_BUILD_1.3.8");

        return view;
    }

}
