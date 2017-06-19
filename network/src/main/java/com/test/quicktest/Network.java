 package com.test.quicktest;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.havelsan.kife.ccp.dto.ARINCLabelDto;
import com.havelsan.kife.ccp.dto.AircraftInfoDto;
import com.havelsan.kife.ccp.dto.CCPResponse;
import com.havelsan.kife.ccp.dto.DiscreteDto;
import com.havelsan.kife.ccp.dto.PartNumberDto;
import com.havelsan.kife.ccp.dto.SystemEquipment;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
/**
 * Created by asd on 13.6.2017.
 */

public class Network {

    final String BASE_URL = "http://192.168.43.237:8080/";
    //final String BASE_URL = "http://httpbin.org/";

    ControlPanelClient client;

    public interface ControlPanelClient {
        @GET("/api/aircraft/get")
        Call<AircraftInfoDto>aircraftInfo();

        @GET("/api/partnumber/get")
        Call<PartNumberDto>partNumber();

        @GET("/api/server/ccp")
        Call<CCPResponse>ccpResponse();

        @GET("/api/server/system")
        Call<SystemEquipment>systemEquipment();

        @GET("/api/discrete/all")
        Call<List<DiscreteDto>> discreteList();

        @GET("/api/arinc/get")
        Call<List<ARINCLabelDto>>arincList();
    }

    /** aircraft info */
    void getAircraftInfo(final NetworkListener listener) { setRequest(client.aircraftInfo(), listener); }
    public String getAircraftInfo(){return getUrl(client.aircraftInfo());}

    /** part number */
    void getPartNumber(final NetworkListener listener) { setRequest(client.partNumber(), listener); }
    public String getPartNumber(){return getUrl(client.partNumber());}

    /** ccp response */
    public void getCCPResponse(final NetworkListener listener) { setRequest(client.ccpResponse(), listener); }
    public String getCCPResponse(){return getUrl(client.ccpResponse());}

    /** system equipment */
    void getSystemEquipment(final NetworkListener listener) { setRequest(client.systemEquipment(), listener); }
    public String getSystemEquipment(){return getUrl(client.systemEquipment());}

    /** discrete list */
    void getDiscreteList(final NetworkListener listener) { setRequest(client.discreteList(), listener);}
    public String getDiscreteList(){return getUrl(client.discreteList());}

    /** arinc list */
    void getArincList(final NetworkListener listener) { setRequest(client.arincList(), listener);}
    public String getArincList(){return getUrl(client.arincList());}


    ///////////////////////////////////////////////////////////////////////////////



    public Network() {
        // Creates the json object which will manage the information received
        GsonBuilder builder = new GsonBuilder();

        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });

        Gson gson = builder.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))

                .build();
        client =  retrofit.create(ControlPanelClient.class);
    }

    private void setRequest(Call call, final NetworkListener listener) {
        Callback callback = new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Object data = response.body();
                Log.i("!!!", "restApi.onResponse");
                listener.onResponse(data);
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.i("!!!", "restApi.onFailure: " + t.getCause());
                listener.onError();
            }
        };

        call.enqueue(callback);
    }

    private String getUrl(Call<?> call) {
        return call.request().url().toString();
    }
}
