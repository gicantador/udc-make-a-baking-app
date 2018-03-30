package com.pgcn.udcmakeabaking.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Created by Giselle on 29/03/2018.
 */

public class LayoutUtil {
    private static final String TAG = LayoutUtil.class.getSimpleName();

    public static int numberOfColumns(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        displayMetrics = context.getResources().getDisplayMetrics();
        //      context.
        //    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthDivider = 600;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;

        if (nColumns < 1) return 1;
        return nColumns;
    }


    /**
     * https://stackoverflow.com/questions/9279111/determine-if-the-device-is-a-smartphone-or-tablet
     *
     * @return
     */
    public static boolean isTablet(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        //  getWindowManager().getDefaultDisplay().getMetrics(metrics);
        metrics = context.getResources().getDisplayMetrics();

        float yInches = metrics.heightPixels / metrics.ydpi;
        float xInches = metrics.widthPixels / metrics.xdpi;
        double diagonalInches = Math.sqrt(xInches * xInches + yInches * yInches);

        if (diagonalInches >= 6.5) {
            // 6.5inch device or bigger
            Log.d(TAG, "isTablet true");
            return true;
        } else {
            Log.d(TAG, "isTablet false");
            return false;
        }
    }

//    public static int larguraTelaComPorcentagem(Context context) {
//
//        Display display = context.getWindowManager().getDefaultDisplay();
//        Point size = new Point();
//        try {
//            display.getRealSize(size);
//        } catch (NoSuchMethodError err) {
//            display.getSize(size);
//        }
//        int width = size.x;
//        int height = size.y;
//               LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((int)(width/3),
//                LinearLayout.LayoutParams.WRAP_CONTENT); // or set height to any fixed value you want
//
//        your_layout.setLayoutParams(lp);
//// OR
//        your_textView.setLayoutParams(lp);
//
//
//        DisplayMetrics displayMetrics = new DisplayMetrics();
//        displayMetrics = context.getResources().getDisplayMetrics();
//        int width = displayMetrics.widthPixels;
//
//        return percentual * width / 100;
//    }
}
