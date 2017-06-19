package com.test.quicktest;

import android.os.Handler;
import android.util.Log;

import java.util.HashMap;


/**
 * Created by asd on 16.6.2017.
 */

public class RestApi {

    final int MODE = 3; //1: her zaman server'a git, 2: bağlantı yoksa mapten kullan, 3: her zaman map'ten kullan

    private static final RestApi singleton = new RestApi();
    HashMap<String, Object> map;
    Network network;
    boolean isChanged = true;

    public static RestApi getInstance() {
        return singleton;
    }

    private RestApi() {
        network = new Network();
        map = Disk.load();
        Log.i("!!!", "rest api map size: " + map.size());
    }

    public void getAircraftInfo(final ResponseListener listener) {

        final String url = network.getAircraftInfo();
        if(isNew(url, listener)) {
            network.getAircraftInfo(getListener(url, listener));
        }

    }

    public void getPartNumber(final ResponseListener listener) {

        final String url = network.getPartNumber();
        if(isNew(url, listener)) {
            network.getPartNumber(getListener(url, listener));
        }

    }

    public void getCCPResponse(final ResponseListener listener) {

        final String url = network.getCCPResponse();
        if(isNew(url, listener)) {
            network.getCCPResponse(getListener(url, listener));
        }
    }

    public void getSystemEquipment(final ResponseListener listener) {

        final String url = network.getSystemEquipment();
        if(isNew(url, listener)) {
            network.getSystemEquipment(getListener(url, listener));
        }
    }

    public void getDiscreteList(final ResponseListener listener) {

        final String url = network.getDiscreteList();
        if(isNew(url, listener)) {
            network.getDiscreteList(getListener(url, listener));
        }
    }

    public void getArincList(final ResponseListener listener) {

        final String url = network.getArincList();
        if(isNew(url, listener)) {
            network.getArincList(getListener(url, listener));
        }
    }
    //////////////////////////////////////////////////////////////////

    /** Verilen key ve listener ile tüm request'lerde kullanılabilen generic bir NetworkListener oluşturur.*/
    NetworkListener getListener(final String key, final ResponseListener listener) {
        return  new NetworkListener() {
            @Override
            public void onResponse(Object data) {
                save(key, data);
                listener.onResponse(data);
            }

            @Override
            public void onError() {

            }
        };
    }

    /** url map içinde kayıtlı ise kayıtlı olan response objesini listener'a gönder */
    private boolean isNew(String url, ResponseListener listener) {
        if(map.containsKey(url)) {
            Object o = map.get(url);
            listener.onResponse(o);
            return false;
        }
        return true;
    }

    private void save(String key, Object data) {
        map.put(key, data);
        isChanged = true;
        Log.i("!!!", "response saved for: " + key);
    }

    public void save() {
        if(isChanged) {
            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    Disk.save(map);
                }
            });
        }
    }
}
