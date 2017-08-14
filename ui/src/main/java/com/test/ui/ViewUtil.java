package com.test.ui;

import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by asd on 15.6.2017.
 */

public class ViewUtil {
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
}
