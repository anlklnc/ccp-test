package com.test.ui.fragments;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by asd on 15.6.2017.
 */

public class BaseFragment extends Fragment {
    void setTint(View v, int colorID) {
        ((ImageView)v).setColorFilter(ContextCompat.getColor(getContext(), colorID));
    }

    Dialog getDialog(int layoutId, View v) {
        Dialog d = new Dialog(getContext());
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(layoutId);

        Window window = d.getWindow();
        window.getAttributes().gravity = Gravity.TOP | Gravity.LEFT;
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        int[] xyPosition = new int[2];
        v.getLocationInWindow(xyPosition);
        window.getAttributes().x = xyPosition[0]+100;
        window.getAttributes().y = xyPosition[1]+50;
        return d;
    }
}
