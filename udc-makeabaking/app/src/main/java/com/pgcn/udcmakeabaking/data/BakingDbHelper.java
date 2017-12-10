package com.pgcn.udcmakeabaking.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Giselle on 10/12/2017.
 */

public class BakingDbHelper extends SQLiteOpenHelper {

    private static final String TAG = BakingDbHelper.class.getSimpleName();

    public BakingDbHelper(Context context) {
        super(context, BakingContract._DATABASE_NAME, null, BakingContract._DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate ");


        final String SQL_CREATE_BAKING_TABLE = "CREATE TABLE " +
                BakingContract.BakingEntry.TABLE_NAME + " (" +
                BakingContract.BakingEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BakingContract.BakingEntry.COLUMN_BAKING_ID + " INTEGER NOT NULL, " +
                BakingContract.BakingEntry.COLUMN_IMAGE + " TEXT, " +
                BakingContract.BakingEntry.COLUMN_NAME + " TEXT, " +
                BakingContract.BakingEntry.COLUMN_SERVINGS + " INTEGER ," +
                BakingContract.BakingEntry.COLUMN_DTA_CRIACAO + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        Log.d(TAG, "query -- " + SQL_CREATE_BAKING_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_BAKING_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: implementar update table
        Log.d(TAG, "onUpgrade ");
        db.execSQL("DROP TABLE IF EXISTS " + BakingContract.BakingEntry.TABLE_NAME);
        onCreate(db);
    }
}
