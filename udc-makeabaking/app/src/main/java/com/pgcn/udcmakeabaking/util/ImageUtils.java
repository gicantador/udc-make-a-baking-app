package com.pgcn.udcmakeabaking.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Giselle on 20/02/2018.
 */

public class ImageUtils {
    private static final String TAG = ImageUtils.class.getSimpleName();


    /**
     * credits https://stackoverflow.com/questions/8992964/android-load-from-url-to-bitmap
     *
     * @param src
     * @return
     */
    public static Bitmap getBitmapFromURL(String src) throws Exception {
        Log.d(TAG, "Carregar bitmap " + src);
        URL url = new URL(src);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.connect();
        InputStream input = connection.getInputStream();
        return BitmapFactory.decodeStream(input);

    }
}
