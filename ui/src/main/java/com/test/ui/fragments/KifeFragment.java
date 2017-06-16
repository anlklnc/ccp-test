package com.test.ui.fragments;


import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.test.ui.R;

import java.util.ArrayList;

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
        addButtonAnimation(power, new Listt(), '0');
        return view;
    }

    static ArrayList<Character> transitionStates = new ArrayList<>(); //button toggle'larını tutar(açık-kapalı)

    public static void addButtonAnimation(ImageView imageView, final ButtonListener listener, char initialState) {
        final TransitionDrawable transition = (TransitionDrawable) imageView.getDrawable();
        final int index = transitionStates.size();
        transitionStates.add(initialState);
        if(initialState == '1') {
            transition.startTransition(1);
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                char state = transitionStates.get(index);
                if (state == '0') {    //aç
                    transitionStates.set(index, '1');
                    transition.startTransition(200);
                    listener.onClick(true);
                } else if (state == '1') {    //kapa
                    transitionStates.set(index, '0');
                    transition.reverseTransition(200);
                    listener.onClick(false);
                }
            }
        });
    }

    public interface ButtonListener {
        void onClick(boolean enable);
    }

    public class Listt implements ButtonListener{

        @Override
        public void onClick(boolean enable) {
            Log.i("!!!", "onClick: " + enable);
        }
    }
}
