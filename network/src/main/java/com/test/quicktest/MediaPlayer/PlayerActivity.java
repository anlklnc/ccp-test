/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.test.quicktest.MediaPlayer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.util.Util;
import com.test.quicktest.R;

/**
 * An activity that plays media using {@link SimpleExoPlayer}.
 */
public class PlayerActivity extends Activity{

    // Activity lifecycle
    PlayerPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exo_player_activity);
        View rootView = findViewById(R.id.root);
        presenter = new PlayerPresenter(this, rootView, getIntent().getExtras());
    }

    public static void startActivity(Context context , String contentUri, String drmLicenseUrl) {

        Intent intent = new Intent(context, PlayerActivity.class);
        Bundle args = new Bundle();
        args.putString("URI", contentUri);
        args.putString("DRM", drmLicenseUrl);
        intent.putExtras(args);
        context.startActivity(intent);
    }

//    @Override
//    public void onNewIntent(Intent intent) {
//        releasePlayer();
//        shouldAutoPlay = true;
//        clearResumePosition();
//        setIntent(intent);
//    }

    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            presenter.initializePlayer();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 23 || presenter.isPlayerNull())) {
            presenter.initializePlayer();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            presenter.releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            presenter.releasePlayer();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            presenter.initializePlayer();
        } else {
            Toast.makeText(this, getString(R.string.storage_permission_denied), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    // Activity input

//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        // Show the controls on any key event.
//        simpleExoPlayerView.showController();
//        // If the event was not handled then see if the player view can handle it as a media key event.
//        return super.dispatchKeyEvent(event) || simpleExoPlayerView.dispatchMediaKeyEvent(event);
//    }
}
