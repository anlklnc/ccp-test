package com.test.ui.activities;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.test.ui.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PowerStatusActivity extends AppCompatActivity {

    public Typeface tf = null;


    @Bind(R.id.rel_root)ViewGroup root;
    @Bind(R.id.list_zone)ListView zoneList;
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

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        tf = Typeface.createFromAsset(getAssets(), "fonts/RBNo3.1-Book.otf");	//Set the standard font
//
        CustomAdapter adapter = new CustomAdapter(this, R.layout.item_zone);
        zoneList.setAdapter(adapter);

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
                        Snackbar.make(mcu2, "Reset Done!", Snackbar.LENGTH_SHORT)
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

        public CustomAdapter(@NonNull Context context, @LayoutRes int resource) {
            super(context, resource);
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

            if(position == 3) {
                ImageView iw = (ImageView)view.findViewById(R.id.iw_state);
                iw.setImageResource(R.drawable.state_error);
            }
            return view;
        }
    }
}
