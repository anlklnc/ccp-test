package com.test.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by asd on 12.6.2017.
 */

public class SeatGroup extends LinearLayout {


    public SeatGroup(Context context, final SeatClickListener listener, final int side, final int position) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int layoutID = R.layout.layout_seatgroup_right;
        if(side == 2) {
            layoutID = R.layout.layout_seatgroup_left;
        }
        LinearLayout view = (LinearLayout) inflater.inflate(layoutID, this, true);

        if(side == 2) {
            TextView tw = (TextView)view.findViewById(R.id.tw_number);
            tw.setText(""+position);
        }

        //seat onclick
        OnClickListener clickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                String tag = v.getTag()+"";
                listener.onSeatClick(position, tag, v);
            }
        };

        //initialize seats
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.lin_main);
        for(int i=1; i<4; i++) {
            View v = layout.getChildAt(i);
            v.setOnClickListener(clickListener);
        }

        //initialize device
        int deviceIndex = 0;
        if(side == 2) {
            deviceIndex = 4;
        }
        View v = view.findViewById(R.id.btn_seb);
        v.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeviceClick(position, side, v);
            }
        });
    }

    public SeatGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SeatGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface SeatClickListener {
        void onSeatClick(int position, String seat, View v);
        void onDeviceClick(int position, int side, View v);
    }
}
