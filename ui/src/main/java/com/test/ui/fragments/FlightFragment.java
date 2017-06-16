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
public class FlightFragment extends Fragment {

    final int LAYOUT = R.layout.fragment_flight;

    public FlightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(LAYOUT, container, false);
        Context c = getContext();

        LinearLayout layout = (LinearLayout)view.findViewById(R.id.lin_main);

        FlightInfoItem item;
        item = new FlightInfoItem(c, 1, "Flight Number", "TK7083");
        layout.addView(item);
        item = new FlightInfoItem(c, 1, "Destination Airport", "Adnan Menderes");
        layout.addView(item);
        item = new FlightInfoItem(c, 1, "Destination City", "Izmir");
        layout.addView(item);
        item = new FlightInfoItem(c, 1, "Destination Country", "Turkey");
        layout.addView(item);
        item = new FlightInfoItem(c, 1, "Distance to Destination", "1696 km");
        layout.addView(item);
        item = new FlightInfoItem(c, 1, "Time to Destination", "02:07");
        layout.addView(item);
        item = new FlightInfoItem(c, 1, "Estimated Time of Arrival", "16:20");
        layout.addView(item);
        item = new FlightInfoItem(c, 1, "Ground Speed", "796 km/h");
        layout.addView(item);
        item = new FlightInfoItem(c, 1, "Altitude", "10000 feet");
        layout.addView(item);
        item = new FlightInfoItem(c, 1, "Outside Temperature", "20 C");
        layout.addView(item);
        item = new FlightInfoItem(c, 1, "Local Time at Departure", "14:13");
        layout.addView(item);
        item = new FlightInfoItem(c, 1, "Local Time at Destination", "14:13");
        layout.addView(item);


        return view;
    }
}
