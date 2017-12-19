package com.pgcn.udcmakeabaking.util;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Giselle on 19/12/2017.
 */

public class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String RECIPE_JSON_ROOT = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private final static int timeout = 20000; // 20 sec


    /**
     * Connects to the api and retrieve tje data
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        final String DELIMITER_PATTERN = "\\A";
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            urlConnection.setConnectTimeout(timeout);
            urlConnection.setReadTimeout(timeout);
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter(DELIMITER_PATTERN);

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    /**
     * Verifica se device está conectado
     *
     * @param cm
     * @return boolean indicando se device está conectado à internet
     */
    public static boolean isOnline(ConnectivityManager cm) {
        Log.d(TAG, "isOnline");
        if (null != cm) {
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return (null != netInfo && netInfo.isConnected());
        }
        return false;
    }
}
