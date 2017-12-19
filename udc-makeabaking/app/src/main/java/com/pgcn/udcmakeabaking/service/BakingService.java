package com.pgcn.udcmakeabaking.service;

import android.os.AsyncTask;

import com.pgcn.udcmakeabaking.model.Baking;
import com.pgcn.udcmakeabaking.util.BakingJsonUtil;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by Giselle on 11/12/2017.
 */

public class BakingService extends AsyncTask<Object, String, ArrayList<Baking>> {

    private AsyncTaskDelegate delegate = null;

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

        String jsonString = (String) objects[0];


        try {
            return
                    BakingJsonUtil.getSimpleMovieStringsFromJson(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        //  return null;
    }
}
