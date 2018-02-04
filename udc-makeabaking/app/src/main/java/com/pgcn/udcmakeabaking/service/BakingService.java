package com.pgcn.udcmakeabaking.service;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.pgcn.udcmakeabaking.model.Baking;
import com.pgcn.udcmakeabaking.util.NetworkUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giselle on 11/12/2017.
 */

public class BakingService extends AsyncTask<Object, String, ArrayList<Baking>> {

    private AsyncTaskDelegate delegate = null;
    private static final String TAG = BakingService.class.getSimpleName();

    /**
     * No construtor da classe, passamos uma classe responsável por "responder" a requisição após a
     * sua execução Esse responsável é o AsyncTaskDelegate
     *
     * @param responder
     */
    public BakingService(AsyncTaskDelegate responder) {
        this.delegate = responder;
    }


    @Override
    protected ArrayList<Baking> doInBackground(Object... objects) {
        try {
            String jsonString;
            jsonString = NetworkUtils.getResponseFromHttpUrl();

            Gson gson = new GsonBuilder().create();
            Log.d(TAG, "Json string " + jsonString);
            return gson.fromJson(jsonString, new TypeToken<List<Baking>>() {
            }.getType());

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
