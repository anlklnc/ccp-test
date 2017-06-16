package com.test.ui;

import android.content.Context;

/**
 * Created by asd on 7.6.2017.
 */

public interface LeftMenuListener {
    Context getContext();
    void onMenuItemSelected(String tag);
}
