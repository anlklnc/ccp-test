package com.test.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by asd on 20.6.2017.
 */

public class EquipmentItem extends LinearLayout {

    public EquipmentItem(Context context, String label, String value) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_equipment_item, this, true);
        TextView labelView = (TextView) view.findViewById(R.id.tw_label);
        TextView valueView = (TextView) view.findViewById(R.id.tw_value);
        labelView.setText(label);
        valueView.setText(value);

        int colorID = R.color.green;
        valueView.setTextColor(context.getResources().getColor(colorID));
    }

    public EquipmentItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EquipmentItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
