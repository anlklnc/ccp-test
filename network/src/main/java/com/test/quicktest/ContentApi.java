package com.test.quicktest;

/**
 * Created by asd on 12.7.2017.
 */

public class ContentApi {
    private static final ContentApi singleton = new ContentApi();
    ContentNetwork contentNetwork;

    public static ContentApi getInstance() {
        return singleton;
    }

    private ContentApi() {
        contentNetwork = new ContentNetwork();
    }

    public void getMovieInfo(final ResponseListener listener) {

        contentNetwork.getMovieInfo(getListener(listener));
    }

    NetworkListener getListener(final ResponseListener listener) {

        return new NetworkListener() {
            @Override
            public void onResponse(Object data) {
                listener.onResponse(data);
            }

            @Override
            public void onError() {

            }
        };
    }
}
