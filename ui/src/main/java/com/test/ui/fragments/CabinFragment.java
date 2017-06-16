package com.test.ui.fragments;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.test.ui.R;
import com.test.ui.SeatGroup;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class CabinFragment extends BaseFragment implements SeatGroup.SeatClickListener {

    private static final int LAYOUT = R.layout.fragment_cabin;

    int size = 30;

    View previouslySelectedSeat;
    View previouslySelectedDevice;

    public CabinFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(LAYOUT, container, false);

        GridView grid1 = (GridView) view.findViewById(R.id.grid_right);
        registerForContextMenu(grid1);
        GridView grid2 = (GridView) view.findViewById(R.id.grid_left);
        grid1.setAdapter(new ImageAdapter(getContext(),1));
        grid2.setAdapter(new ImageAdapter(getContext(),2));

        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId()==R.id.grid_right || v.getId()==R.id.grid_left) {
            menu.setHeaderTitle(v.getTag()+"");

            String[] menuItems = getResources().getStringArray(R.array.seat_menu);
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int menuItemIndex = item.getItemId();
        switch (menuItemIndex) {
            case 0: //edit
                Log.i("!!!", "onContextItemSelected: 0");
                break;
            case 1: //delete
                Log.i("!!!", "onContextItemSelected: 1");
                break;
        }
        return true;
    }

    @Override
    public void onSeatClick(int position, String seat, final View v) {

        Log.i("!!!", "onSeatClick: " + position+"-"+seat );

        //set new bg highlighted
        v.setBackgroundResource(R.drawable.round1_highlighted);

        //seat options dialog
        Dialog d = getDialog(R.layout.dialog_seat_options, v);

        //title
        TextView title = (TextView) d.findViewById(R.id.tw_title);
        title.setText("Seat " + position + seat);

        //cpu
        int i = new Random().nextInt(60);
        TextView cpuView = (TextView) d.findViewById(R.id.tw_cpu);
        cpuView.setText(i+"%");

        d.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                v.setBackgroundResource(R.drawable.round1);
            }
        });

        d.show();
    }

    @Override
    public void onDeviceClick(int position, int side, final View v) {

        String s;
        if(side == 1) {
            s = "DEF";
        } else {
            s = "ABC";
        }
        Log.i("!!!", "onDeviceClick: " + position +' '+s);

        //set new bg highlighted
        setTint(v, R.color.device_highlighted);

        //seat options dialog
        Dialog d = getDialog(R.layout.dialog_device_options, v);

        //title
        TextView title = (TextView) d.findViewById(R.id.tw_title);
        title.setText("SEB " + position +' '+s);

        //temperature
        int i = new Random().nextInt(20) + 30;
        TextView tempView = (TextView) d.findViewById(R.id.tw_temperature);
        tempView.setText(i+"C");

        d.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                setTint(v, R.color.device_normal);
            }
        });

        d.show();
    }

    public class ImageAdapter extends BaseAdapter {
        private Context mContext;
        private int side;

        public ImageAdapter(Context c, int side) {
            mContext = c;
            this.side = side;
        }

        public int getCount() {
            return size;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            
            SeatGroup view;
            if (convertView == null) {
                // if it's not recycled, initialize some attributes
                view = new SeatGroup(mContext, CabinFragment.this, side, position + 1);
            } else {
                view = (SeatGroup) convertView;
            }
            return view;
        }
    }
}
