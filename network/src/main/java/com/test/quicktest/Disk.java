package com.test.quicktest;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * Created by asd on 16.6.2017.
 */

public class Disk {
    static final String QUICKTEST_SHARED_PREF = "com.networkTest.quicktest";
    static final String NETWORK_RESPONSES = "com.networkTest.quicktest.network_responses";

    static Context context = QuickTest.getInstance().getApplicationContext();
    static final SharedPreferences sp = context.getSharedPreferences(QUICKTEST_SHARED_PREF, Context.MODE_PRIVATE);

    private static SharedPreferences.Editor getEditor() {
        return sp.edit();
    }

    public static synchronized void save(HashMap<String, Object> data) {

        Gson gson = new Gson();
        String serializedTagList = gson.toJson(data);
        getEditor().putString(NETWORK_RESPONSES, serializedTagList).commit();
    }

    public static HashMap load() {

        String serialized = sp.getString(NETWORK_RESPONSES, "");
        if(serialized != null && serialized.length() > 0) {
            Gson gson = new Gson();
            Type type = new TypeToken<HashMap>(){}.getType();
            HashMap data = gson.fromJson(serialized, type);
            return data;
        } else {
            return new HashMap();
        }
    }
}
