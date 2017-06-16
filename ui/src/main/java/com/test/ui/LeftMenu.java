package com.test.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by asd on 7.6.2017.
 */

public class LeftMenu extends LinearLayout {

    View selected;

    Context context;
    LeftMenuListener listener;
    int selectedIconColor = R.color.blue2;
    int unselectedIconColor = R.color.text_color_default;
    int selectedTextColor;
    int unselectedTextColor;

    public LeftMenu(LeftMenuListener listener) {
        super(listener.getContext());
        this.listener = listener;
        context = listener.getContext();
        inflate(context);
    }

    public LeftMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context);
    }

    public LeftMenu(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context);
    }

    void inflate(final Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.content_left_menu, this, true);

        selectedTextColor = getResources().getColor(selectedIconColor);
        unselectedTextColor = getResources().getColor(unselectedIconColor);

        //
        int[] ids = {R.id.lin_flight_info, R.id.lin_equipment, R.id.lin_services, R.id.lin_cabin, R.id.lin_pa, R.id.lin_kife, R.id.lin_media, R.id.lin_shutdown};
        for(int i=0; i<ids.length; i++) {
            final View menuItem = view.findViewById(ids[i]);
            OnClickListener lst = new OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        listener.onMenuItemSelected(v.getTag()+"");
                    } catch (Exception e) {
                        e.printStackTrace();//
                    }

                    //önceki seçileni unselect yap
                    if(selected != null) {
                        select(false);
                    }

                    //yeniyi select yap
                    selected = menuItem;
                    select(true);
                }
            };
            menuItem.setOnClickListener(lst);
        }
    }

    void select(boolean select) {
        int c1, c2;
        if(select) {
            c1 = selectedIconColor;
            c2 = selectedTextColor;
        } else {
            c1 = unselectedIconColor;
            c2 = unselectedTextColor;
        }
        try {
            ImageView icon = (ImageView)selected.findViewById(R.id.iw_left_menu_item);
            icon.setColorFilter(ContextCompat.getColor(context,c1));
            TextView text = (TextView) selected.findViewById(R.id.tw_left_menu_item);
            text.setTextColor(c2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
