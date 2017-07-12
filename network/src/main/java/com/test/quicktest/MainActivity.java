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
import android.widget.TextView;

import com.havelsan.kife.ccp.dto.CCPResponse;
import com.havelsan.kife.ccp.dto.ProcessInfoDto;
import com.havelsan.kife.ccp.dto.ServiceInfoDto;
import com.havelsan.kife.ccp.dto.SystemEquipment;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RestApi restApi;
    TextView tw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        restApi = RestApi.getInstance();

        tw = (TextView)findViewById(R.id.tw_hello);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //networkTest();
                contentTest();
            }
        });

        //networkTest();
        contentTest();
    }

    @Override
    protected void onStop() {
        Log.i("!!!", "onStop: ");
        super.onStop();
    }

    void contentTest() {
        ContentApi.getInstance().getMovieInfo(new ResponseListener() {
            @Override
            public void onResponse(Object o) {
                Log.i("!!!", "onResponse: ");
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
