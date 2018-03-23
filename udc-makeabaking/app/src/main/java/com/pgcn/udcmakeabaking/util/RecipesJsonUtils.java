package com.pgcn.udcmakeabaking.util;

import android.util.Log;

import com.google.gson.Gson;
import com.pgcn.udcmakeabaking.model.Baking;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Giselle on 23/03/2018.
 */

public class RecipesJsonUtils {

    private static final String TAG = RecipesJsonUtils.class.getSimpleName();


    public static ArrayList<Baking> getSimpleBakingStringsFromJson(String jsonBakingResponse) throws JSONException {


        ArrayList<Baking> bakingArrayList = new ArrayList<Baking>();

        JSONArray bakingArray = new JSONArray(jsonBakingResponse);
        //    Log.d(TAG, "bakingArray" + bakingArray);
        Log.d(TAG, "bakingArray.length()" + bakingArray.length());

        Gson gson2 = new Gson();

        for (int i = 0; i < bakingArray.length(); i++) {
            JSONObject finalObject = bakingArray.getJSONObject(i);
            Baking inputbaking = gson2.fromJson(finalObject.toString(), Baking.class);
            Log.d(TAG, "inputbaking  " + inputbaking.getName());
            Log.d(TAG, "inputbaking Ingredients " + inputbaking.getIngredients().size());
            Log.d(TAG, "inputbaking Steps " + inputbaking.getSteps().size());



            bakingArrayList.add(inputbaking);
        }

        return bakingArrayList;

    }


}
