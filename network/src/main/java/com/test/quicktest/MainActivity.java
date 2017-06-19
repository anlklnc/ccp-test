package com.test.quicktest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.havelsan.kife.ccp.dto.ARINCLabelDto;
import com.havelsan.kife.ccp.dto.AircraftInfoDto;
import com.havelsan.kife.ccp.dto.DiscreteDto;
import com.havelsan.kife.ccp.dto.SystemEquipment;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    RestApi restApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        restApi = RestApi.getInstance();

        dummyTest();
        networkTest();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                networkTest();
            }
        });
    }

    void networkTest() {
        restApi.getAircraftInfo(new ResponseListener() {
            @Override
            public void onResponse(Object o) {
                AircraftInfoDto data = (AircraftInfoDto)o;
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
                ArrayList<DiscreteDto> discreteList = (ArrayList<DiscreteDto>)o;
                Log.i("!!!", "onResult: all discretes");
                for (DiscreteDto dto:discreteList) {
                    Log.i("!!!", "Discrete: " + dto.getName() +"->"+ dto.getValue());
                }
            }
        });
        restApi.getArincList(new ResponseListener() {
            @Override
            public void onResponse(Object o) {
                Log.i("!!!", "onResult: arinc list");
                ArrayList<ARINCLabelDto> arincList = (ArrayList<ARINCLabelDto>)o;
                for (ARINCLabelDto dto:arincList) {
                    Log.i("!!!", "Arinc: " + dto.getLabelNumber()+"->"+dto.getName()+dto.getData()+"->"+dto.getWordCount()+"->"+dto.getDateTime()+"->"+dto.getChannel());
                }
            }
        });
    }

    void dummyTest() {
        HashMap map = Disk.load();
        Log.i("!!!", "dummyTest: ");
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
        restApi.save(); //çıkış yapmadan önce yeni data varsa kaydet
        super.onDestroy();
    }
}