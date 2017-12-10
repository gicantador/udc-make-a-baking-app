package com.pgcn.udcmakeabaking.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Giselle on 10/12/2017.
 */

public class StepsDbHelper extends SQLiteOpenHelper {

    private static final String TAG = StepsDbHelper.class.getSimpleName();


    public StepsDbHelper(Context context) {
        super(context, BakingDatabaseContract._DATABASE_NAME, null, BakingDatabaseContract._DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate ");


        final String SQL_CREATE_STEP_TABLE = "CREATE TABLE " +
                StepContract.StepsEntry.TABLE_NAME + " (" +
                StepContract.StepsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                StepContract.StepsEntry.COLUMN_SORT_ID + " INTEGER NOT NULL, " +
                StepContract.StepsEntry.COLUMN_BAKING_ID + " INTEGER NOT NULL, " +
                StepContract.StepsEntry.COLUMN_DESCRIPTION + " TEXT ," +
                StepContract.StepsEntry.COLUMN_SHORT_DESCRIPTION + " TEXT, " +
                StepContract.StepsEntry.COLUMN_THUMBNAIL_URL + " TEXT, " +
                StepContract.StepsEntry.COLUMN_VIDEO_URL + " TEXT, " +
                StepContract.StepsEntry.COLUMN_DTA_CRIACAO + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        Log.d(TAG, "query -- " + SQL_CREATE_STEP_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_STEP_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: implementar update table
        Log.d(TAG, "onUpgrade ");
        db.execSQL("DROP TABLE IF EXISTS " + StepContract.StepsEntry.TABLE_NAME);
        onCreate(db);
    }
}
