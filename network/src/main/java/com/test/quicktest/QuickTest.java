package com.test.quicktest;

import android.app.Application;
/**
 * Created by asd on 13.6.2017.
 */

public class QuickTest extends Application {

    private static QuickTest singleton;

    public static QuickTest getInstance() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Setup singleton instance
        singleton = this;
    }
}
