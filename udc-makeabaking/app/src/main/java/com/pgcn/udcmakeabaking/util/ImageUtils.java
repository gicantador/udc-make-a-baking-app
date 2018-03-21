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


    public static Bitmap getBlankkPlacehoder() {
        Log.d(TAG, "getBlankkPlacehoder");
        return Bitmap.createBitmap(10, 10, Bitmap.Config.RGB_565);

    }

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
        Bitmap myBitmap = BitmapFactory.decodeStream(input);
        return myBitmap;

    }
}
