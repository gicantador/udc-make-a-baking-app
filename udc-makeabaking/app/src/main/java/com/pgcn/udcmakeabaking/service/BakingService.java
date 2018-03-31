package com.pgcn.udcmakeabaking.service;

import android.os.AsyncTask;
import android.util.Log;

import com.pgcn.udcmakeabaking.model.Baking;
import com.pgcn.udcmakeabaking.util.NetworkUtils;
import com.pgcn.udcmakeabaking.util.RecipesJsonUtils;

import java.util.ArrayList;

/**
 * Created by Giselle on 23/03/2018.
 */

public class BakingService extends AsyncTask<Object, String, ArrayList<Baking>> {
    private static final String TAG = BakingService.class.getSimpleName();


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

        try {

            String jsonBakingResponse = NetworkUtils.getResponseFromHttpUrl();

            return RecipesJsonUtils.getSimpleBakingStringsFromJson(jsonBakingResponse);


        } catch (Exception e) {
            Log.e(TAG, " doInBackground ", e);
            e.printStackTrace();
            return null;
        }
    }



    @Override
    protected void onPostExecute(ArrayList<Baking> bakings) {
        super.onPostExecute(bakings);
        if (delegate != null)
            delegate.processFinish(bakings);
    }




    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (delegate != null)
            delegate.preExecute();
    }
}
