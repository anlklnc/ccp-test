package com.test.quicktest;


import android.os.Handler;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by asd on 16.6.2017.
 */

public class RestApi {

    final int MODE = 2; //1: her zaman server'a git, 2: bağlantı yoksa mapten kullan, 3: her zaman map'ten kullan

    private static final RestApi singleton = new RestApi();
    HashMap<String, Object> map;
    Network network;
    ContentNetwork contentNetwork;
    boolean isChanged = false;

    public static RestApi getInstance() {
        return singleton;
    }

    private RestApi() {

        network = new Network();
        contentNetwork = new ContentNetwork();

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

    public void getCabinEquipment(final ResponseListener listener) {

        final String url = network.getCabinEquipment();
        if(isNew(url, listener)) {
            network.getCabinEquipment(getListener(url, listener));
        }
    }

    public void getFlightInfo(final ResponseListener listener) {

        final String url = network.getFlightInfo();
        if(isNew(url, listener)) {
            network.getFlightInfo(getListener(url, listener));
        }
    }

    public void subscribeSomething() {

    }

    //CONTENT
    public void getMovieInfo(final ResponseListener listener) {

        final String url = contentNetwork.getMovieInfo();
        if(isNew(url, listener)) {
            contentNetwork.getMovieInfo(getListener(url, listener));
        }
    }

    //////////////////////////////////////////////////////////////////

    /** Verilen key ve listener ile tüm request'lerde kullanılabilen generic bir NetworkListener oluşturur.*/
    NetworkListener getListener(final String url, final ResponseListener listener) {

        return  new NetworkListener() {
            @Override
            public void onResponse(Object data) {
                if(data != null) {
                    save(url, data);
                }
                listener.onResponse(data);
            }

            @Override
            public void onError() {
                if(MODE == 2) {
                    Object data = map.get(url);
                    listener.onResponse(data);
                }
            }
        };
    }

    /** url map içinde kayıtlı ise kayıtlı olan response objesini listener'a gönder */
    private boolean isNew(String url, ResponseListener listener) {
        Log.i("!!!", "request");
        if(MODE == 3 && map.containsKey(url)) {
            Object data = map.get(url);
            listener.onResponse(data);
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


