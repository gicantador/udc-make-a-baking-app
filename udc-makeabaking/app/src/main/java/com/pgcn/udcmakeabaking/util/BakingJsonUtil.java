package com.pgcn.udcmakeabaking.util;

import android.util.Log;

import com.google.gson.Gson;
import com.pgcn.udcmakeabaking.model.Baking;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by Giselle on 19/12/2017.
 */

public class BakingJsonUtil {

    private static final String TAG = BakingJsonUtil.class.getSimpleName();
    private static final String MESSAGE_CODE = "status_code";
    private static final String BAKING_INGREDIENTS_LIST = "ingredients";
    private static final String BAKING_STEPS_LIST = "steps";
    private static final String BAKING_ID = "id";
    private static final String BAKING_NAME = "name";
    private static final String BAKING_SERVINGS = "servings";
    private static final String BAKING_IMAGE = "image";


    public static ArrayList<Baking> getSimpleMovieStringsFromJson(String bakingJsonStr) throws
            JSONException {

        ArrayList<Baking> bakingArrayList = new ArrayList<Baking>();
        JSONObject bakingJson = new JSONObject(bakingJsonStr);

        verificaRetorno(bakingJson);


        Gson gson = new Gson();

        JSONArray bakingArray = bakingJson.getJSONArray("");
        for (int i = 0; i < bakingArray.length(); i++) {
            JSONObject finalObject = bakingArray.getJSONObject(i);
            Baking baking = gson.fromJson(finalObject.toString(), Baking.class);
            bakingArrayList.add(baking);
        }
/*
        for (int i = 0; i < ingredientsArray.length(); i++) {
            JSONObject finalObject = ingredientsArray.getJSONObject(i);
            Ingredient inputIngredient = gson2.fromJson(finalObject.toString(), Ingredient.class);
            ingredientsArray.add(inputIngredient);
        }

       /*
        if (bakingJsonStr.has(BAKING_INGREDIENTS_LIST)) {
            JSONArray ingredientsArray = bakingJson.getJSONArray(BAKING_INGREDIENTS_LIST);
            Gson gson2 = new Gson();

            for (int i = 0; i < ingredientsArray.length(); i++) {
                JSONObject finalObject = ingredientsArray.getJSONObject(i);
                Ingredient inputIngredient = gson2.fromJson(finalObject.toString(), Ingredient.class);
                ingredientsArray.add(inputIngredient);
            }
        }
        if (bakingJsonStr.has(BAKING_STEPS_LIST)) {
            JSONArray stepsArray = bakingJson.getJSONArray(BAKING_STEPS_LIST);
            Gson gson2 = new Gson();

            for (int i = 0; i < stepsArray.length(); i++) {
                JSONObject finalObject = stepsArray.getJSONObject(i);
                Step inputStep = gson2.fromJson(finalObject.toString(), Step.class);
                stepsArray.add(inputStep);
            }
        }*/
        return bakingArrayList;

    }

    /**
     * Verifica o c[odigo de retorno
     *
     * @param jsonObject
     * @throws JSONException
     */
    private static void verificaRetorno(JSONObject jsonObject) throws JSONException {
        if (jsonObject.has(MESSAGE_CODE)) {
            int errorCode = jsonObject.getInt(MESSAGE_CODE);
            Log.d(TAG, "Codigo de retorno " + String.valueOf(errorCode));
            switch (errorCode) {
                case HttpURLConnection.HTTP_OK:
                    Log.d(TAG, "Tudo ok. ");
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    Log.d(TAG, "Location invalid. ");
                    throw new JSONException("Error code " + HttpURLConnection.HTTP_NOT_FOUND);
                default:
                    Log.d(TAG, "Server probably down. ");
                    throw new JSONException("Error code " + errorCode);
            }
        }
    }

}
