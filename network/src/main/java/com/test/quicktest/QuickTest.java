package com.test.quicktest;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

/**
 * Created by asd on 13.6.2017.
 */

public class QuickTest extends Application {
    @Override public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
    }
}
