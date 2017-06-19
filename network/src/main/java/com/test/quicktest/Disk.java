package com.test.quicktest;

import android.content.Context;
import android.content.SharedPreferences;

import com.thoughtworks.xstream.XStream;

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

    public static synchronized void save(HashMap<String, Object> map) {

        CacheData obj = new CacheData(map);
        String serialized = serialize(obj);
        getEditor().putString(NETWORK_RESPONSES, serialized).commit();
    }

    public static HashMap load() {

        String serialized = sp.getString(NETWORK_RESPONSES, "");
        if(serialized != null && serialized.length() > 0) {
            CacheData data = (CacheData) deserialize(serialized);
            return data.map;
        } else {
            return new HashMap();
        }
    }

    private static String serialize(Object obj) {
        XStream xstream = new XStream();
        String serialized = xstream.toXML(obj);
        return serialized;
    }

    private static Object deserialize(String data) {
        XStream xstream = new XStream();
        Object obj = xstream.fromXML(data);
        return obj;
    }
}
