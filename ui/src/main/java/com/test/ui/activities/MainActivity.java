package com.test.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.FrameLayout;

import com.test.ui.LeftMenu;
import com.test.ui.LeftMenuListener;
import com.test.ui.R;
import com.test.ui.fragments.CabinFragment;
import com.test.ui.fragments.FlightFragment;
import com.test.ui.fragments.KifeFragment;
import com.test.ui.fragments.MediaFragment;
import com.test.ui.fragments.SystemEquipmentFragment;
import com.test.ui.fragments.SystemInformationFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LeftMenuListener {

    final int FRAGMENT_FLIGHT = 0;
    final int FRAGMENT_EQUIPMENT = 1;
    final int FRAGMENT_INFORMATION = 2;
    final int FRAGMENT_KIFE = 3;
    final int FRAGMENT_MEDIA = 4;
    final int FRAGMENT_SHUTDOWN = 5;
    final int FRAGMENT_CABIN = 6;

    @Bind(R.id.fr_left_menu)FrameLayout leftMenuLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        LeftMenu menu = new LeftMenu(this);
        leftMenuLayout.addView(menu);
//        onMenuItemSelected("flight");
//        onMenuItemSelected("cabin");
        onMenuItemSelected("information");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onMenuItemSelected(String tag) {
        int fragmentID;
        Fragment targetFragment = null;
        if (tag.equals("flight")) {
            fragmentID = FRAGMENT_FLIGHT;
            targetFragment = new FlightFragment();
            //mTitle = getString(R.string.tab_home);

        } else if (tag.equals("equipment")) {
            fragmentID = FRAGMENT_EQUIPMENT;
            targetFragment = new SystemEquipmentFragment();
        } else if (tag.equals("information")) {
            fragmentID = FRAGMENT_INFORMATION;
            targetFragment = new SystemInformationFragment();
        }
        else if(tag.equals("kife")) {
            fragmentID = FRAGMENT_KIFE;
            targetFragment = new KifeFragment();
        }
        else if(tag.equals("media")) {
            fragmentID = FRAGMENT_MEDIA;
            targetFragment = new MediaFragment();
        }
        else if(tag.equals("shutdown")) {
            fragmentID = FRAGMENT_SHUTDOWN;
            //targetFragment = new ShutdownFragment();
        } else if(tag.equals("cabin")) {
            fragmentID = FRAGMENT_CABIN;
            targetFragment = new CabinFragment();
        }

        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        try {
            fragmentManager.beginTransaction()
                    //.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .replace(R.id.container, targetFragment, tag)
                    .commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
