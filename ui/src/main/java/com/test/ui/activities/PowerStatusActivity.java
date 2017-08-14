package com.test.ui.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.test.ui.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PowerStatusActivity extends AppCompatActivity {

    public Typeface tf = null;

    View selectedPipe;

    @Bind(R.id.rel_root)ViewGroup root;
    @Bind(R.id.list_zone_1)ListView zoneList1;
    @Bind(R.id.list_zone_2)ListView zoneList2;
    @Bind(R.id.v_mcu1)View mcu1;
    @Bind(R.id.v_mcu2)View mcu2;
    @Bind(R.id.iw_mcu_2)ImageView mcuInner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_status);
        ButterKnife.bind(this);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        tf = Typeface.createFromAsset(getAssets(), "fonts/RBNo3.1-Book.otf");	//Set the standard font

        CustomAdapter adapter1 = new CustomAdapter(1, this, R.layout.item_zone);
        zoneList1.setAdapter(adapter1);
        zoneList1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPipe = ((ViewGroup)view).getChildAt(3);
                selectedPipe.setVisibility(View.VISIBLE);
                createZoneDialog(1, position);
            }
        });

        CustomAdapter adapter2 = new CustomAdapter(2, this, R.layout.item_zone);
        zoneList2.setAdapter(adapter2);
        zoneList2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPipe = ((ViewGroup)view).getChildAt(3);
                selectedPipe.setVisibility(View.VISIBLE);
                createZoneDialog(2, position);
            }
        });


        mcu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mcuInner2.setImageDrawable(null);
                mcu2.setBackgroundResource(R.drawable.iax_animation);
                final AnimationDrawable frameAnimation;
                frameAnimation = (AnimationDrawable)mcu2.getBackground();
                frameAnimation.start();

                Handler h = new Handler();
                h.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frameAnimation.stop();
                        mcu2.setBackgroundResource(R.drawable.sip_on);
                        mcuInner2.setImageResource(R.drawable.ic_refresh);
                        Snackbar.make(mcu2, "Refresh Done!", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }
                }, 5000);
            }
        });

        test(root);
    }


    void test(ViewGroup vg) {
        for(int i=0; i<vg.getChildCount(); i++) {

            Object view = vg.getChildAt(i);

            if(view instanceof ViewGroup) {
                test((ViewGroup) view);

            } else if(view instanceof TextView) {
                ((TextView)view).setTypeface(tf);
            }
        }
    }

    public class CustomAdapter extends ArrayAdapter<String> {

        LayoutInflater inflater;
        int listId;

        public CustomAdapter(int listId, @NonNull Context context, @LayoutRes int resource) {
            super(context, resource);
            this.listId = listId;
            inflater = (LayoutInflater) PowerStatusActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = inflater.inflate(R.layout.item_zone, null);

            TextView label = (TextView)view.findViewById(R.id.tw_label);
            label.setText("Zone "+(position+1));
            label.setTypeface(tf);

            //todo mcu çıkışlarına göre kırmızı ya da yeşil(test olarak zone 1 ve 2 kırmızı)
            if(listId == 2 && position == 1) {
                View v = view.findViewById(R.id.v_pipe_3);
                v.setBackgroundResource(R.color.red2);
            }

            //todo zone on/off state(test olarak zone 4 off)
            if(listId == 1 && position == 3) {
                ImageView iw = (ImageView)view.findViewById(R.id.iw_state);
                iw.setImageResource(R.drawable.state_error);
            }

            //bu hiçbir zaman gözükmeyecek
            if(position == 4) {
                View v = view.findViewById(R.id.v_pipe_2);
                v.setVisibility(View.GONE);
            }
            return view;
        }
    }

    void createZoneDialog(int mcu, int position) {
        Dialog d = new Dialog(this);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.dialog_zone_details);

        Window window = d.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.getAttributes().x = 100;

        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        if(mcu == 1) {
            window.getAttributes().y = 100;
            window.getAttributes().gravity = Gravity.TOP | Gravity.RIGHT;
        } else if(mcu == 2) {
            window.getAttributes().y = 50;
            window.getAttributes().gravity = Gravity.BOTTOM | Gravity.RIGHT;
        }



        View v1 = d.findViewById(R.id.inc_port_1);
        init(v1, position, 1);
        View v2 = d.findViewById(R.id.inc_port_2);
        init(v2, position, 2);
        View v3 = d.findViewById(R.id.inc_port_3);
        init(v3, position, 3);

        d.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                selectedPipe.setVisibility(View.INVISIBLE);
            }
        });

        d.show();
    }

    void init(View v, int p1, int p2) {
        p1++;

        //faz durumuna göre yeşil-kırmızı(test olarak faz1 kapalı)
        if(p2 == 1) {
            View pipe1 = v.findViewById(R.id.v_pipe_1);
            pipe1.setBackgroundResource(R.color.red2);
            View pipe2 = v.findViewById(R.id.v_pipe_2);
            pipe2.setBackgroundResource(R.color.red2);
        }

        final ToggleButton toggle = (ToggleButton) v.findViewById(R.id.iw_port_button);
        toggle.setTypeface(tf);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    toggle.setTextColor(getResources().getColor(R.color.gray3));
                } else {
                    // The toggle is disabled
                    toggle.setTextColor(getResources().getColor(R.color.white_2));
                }
            }
        });

        //todo port state'ine göre checked(test olarka unchecked)
        if(p2==2) {
            toggle.setChecked(false);
        }

        TextView fuse = (TextView) v.findViewById(R.id.tw_zone_fuse);
        fuse.setText(p1+"."+p2);
        TextView port = (TextView) v.findViewById(R.id.tw_zone_port);
        port.setText(p1+"."+p2);
    }
}
