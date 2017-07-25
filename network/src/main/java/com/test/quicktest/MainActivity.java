package com.test.quicktest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.exoplayer2.C;
import com.havelsan.kife.ccp.dto.CCPResponse;
import com.havelsan.kife.ccp.dto.ProcessInfoDto;
import com.havelsan.kife.ccp.dto.ServiceInfoDto;
import com.havelsan.kife.ccp.dto.SystemEquipment;
import com.havelsan.kife.ccp.dto.UserDto;
import com.test.quicktest.MediaPlayer.PlayerActivity;
import com.test.quicktest.MediaPlayer.PlayerFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    RestApi restApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        restApi = RestApi.getInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                test();
            }
        });

        //

    }

    void test() {
        fragmentDialogTest();
    }

    void fragmentDialogTest() {
        FragmentManager fm = getSupportFragmentManager();
        PlayerFragment fragment = PlayerFragment.newInstance();
        fragment.show(fm, "fragment_edit_name");
    }

    void mediaTest() {
        //todo
        boolean preferExtensionDecoders = false;
        String uri = "https://planet.thy.com/mediacontent/audio/Pentagram_Akustik/Pentagram_Akustik-006.mp3";
//                String uri = "https://planet.thy.com/mediacontent/video/tkhv1704M00375w.mpd";
        String drmLicenseUrl = null;
//                String drmLicenseUrl = "https://planet.thy.com:9999/proxy?assetid=tkhv1704M00375w";
        String[] drmKeyRequestProperties = null;
        UUID drmSchemeUuid = C.WIDEVINE_UUID;
        String extension = null;

        Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
        intent.putExtra(PlayerActivity.PREFER_EXTENSION_DECODERS, preferExtensionDecoders);
        if (drmSchemeUuid != null) {
            intent.putExtra(PlayerActivity.DRM_SCHEME_UUID_EXTRA, drmSchemeUuid.toString())
                    .putExtra(PlayerActivity.DRM_LICENSE_URL, drmLicenseUrl)
                    .putExtra(PlayerActivity.DRM_KEY_REQUEST_PROPERTIES, drmKeyRequestProperties);
        }

        intent.setData(Uri.parse(uri))
                .putExtra(PlayerActivity.EXTENSION_EXTRA, extension)
                .setAction(PlayerActivity.ACTION_VIEW);

        startActivity(intent);
        //todo
    }

    void httpPostTest(){
        UserDto dto = new UserDto();
        dto.setUsername("foo");
        dto.setPassword("bar");
        RestApi.getInstance().authenticate(dto, new ResponseListener() {
            @Override
            public void onResponse(Object o) {
                if(o != null) {
                    Log.i("!!!", "onResponse: ");
                }
            }
        });
    }


    void contentTest() {
        RestApi.getInstance().getMovieInfo(new ResponseListener() {
            @Override
            public void onResponse(Object o) {
                ArrayList<Movie> list = (ArrayList<Movie>)o;
                for(Movie m:list) {
                    Log.i("!!!", "movie: "+m.getId()+" - "+m.getName()+" - "+m.getArtist());
                }
            }
        });
    }

    void networkTest() {

//        tw.setText("test");
//        restApi.subscribe(tw);
        restApi.getAircraftInfo(new ResponseListener() {
            @Override
            public void onResponse(Object o) {
//                AircraftInfoDto data = (AircraftInfoDto)o;
                Log.i("!!!", "onResult: aircraft info");
            }
        });

        restApi.getPartNumber(new ResponseListener() {
            @Override
            public void onResponse(Object o) {
                Log.i("!!!", "onResult: part number");
            }
        });
        restApi.getCCPResponse(new ResponseListener() {
            @Override
            public void onResponse(Object o) {
                Log.i("!!!", "onResult: ccp response");
                CCPResponse response = (CCPResponse)o;
                List<ServiceInfoDto> list = response.getServices();
                for (ServiceInfoDto item:list) {
                    String name = item.getDisplayName();
                    String state = item.getState();
                    String cpu = item.getCpuUsage();
                    String ram = item.getRamuUsage();
                    Log.i("!!!", "Service: " + name+"-"+state+"-"+cpu+"-"+ram);
                }

                List<ProcessInfoDto> listt = response.getProcesses();
                for(ProcessInfoDto item:listt) {
                    String name = item.getCaption();//name
                    String cpu = item.getCpuUsage();
                    String ram = item.getRamUsage();
                    Log.i("!!!", "Process: " + name+"-"+cpu+"-"+ram);
                }
            }
        });
        restApi.getSystemEquipment(new ResponseListener() {
            @Override
            public void onResponse(Object o) {
                SystemEquipment eq = (SystemEquipment)o;
                Log.i("!!!", "onResult: system equipment");
            }
        });
        restApi.getDiscreteList(new ResponseListener() {
            @Override
            public void onResponse(Object o) {
                Log.i("!!!", "onResult: all discretes");
//                ArrayList<DiscreteDto> discreteList = (ArrayList<DiscreteDto>)o;
//                for (DiscreteDto dto:discreteList) {
//                    Log.i("!!!", "Discrete: " + dto.getName() +"->"+ dto.getValue());
//                }
            }
        });
        restApi.getArincList(new ResponseListener() {
            @Override
            public void onResponse(Object o) {
                Log.i("!!!", "onResult: arinc list");
//                ArrayList<ARINCLabelDto> arincList = (ArrayList<ARINCLabelDto>)o;
//                for (ARINCLabelDto dto:arincList) {
//                    Log.i("!!!", "Arinc: " + dto.getLabelNumber()+"->"+dto.getName()+dto.getData()+"->"+dto.getWordCount()+"->"+dto.getDateTime()+"->"+dto.getChannel());
//                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        Log.i("!!!", "onDestroy: ");
        restApi.save(); //çıkış yapmadan önce yeni data varsa kaydet
        super.onDestroy();
    }
}
