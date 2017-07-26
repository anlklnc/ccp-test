package com.test.quicktest.MediaPlayer;

import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.Toast;

import com.google.android.exoplayer2.util.Util;
import com.test.quicktest.R;

/**
 * Created by asd on 25.7.2017.
 */

public class PlayerFragment extends DialogFragment{

    private Context context;
    Dialog dialog;
    PlayerPresenter presenter;

    public static PlayerFragment newInstance() {
        Bundle args = new Bundle();
        args.putString("URI", "https://planet.thy.com/mediacontent/audio/Pentagram_Akustik/Pentagram_Akustik-006.mp3");
        //                String uri = "https://planet.thy.com/mediacontent/video/tkhv1704M00375w.mpd";
        //args.putString("DRM", "https://planet.thy.com:9999/proxy?assetid=tkhv1704M00375w");

        PlayerFragment fragment = new PlayerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.exo_player_activity);
        dialog.show();
        dialog.getWindow().setLayout(1000, 300);

        View rootView = dialog.findViewById(R.id.root);

        presenter = new PlayerPresenter(context, getActivity(), rootView, getArguments());

        return dialog;
    }

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
            Toast.makeText(context, context.getString(R.string.storage_permission_denied), Toast.LENGTH_LONG).show();
            dialog.dismiss();
        }
    }
}
