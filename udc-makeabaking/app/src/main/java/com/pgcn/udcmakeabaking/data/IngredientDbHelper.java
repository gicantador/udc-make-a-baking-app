package com.pgcn.udcmakeabaking.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Giselle on 10/12/2017.
 */

public class IngredientDbHelper extends SQLiteOpenHelper {

    private static final String TAG = IngredientDbHelper.class.getSimpleName();

    public IngredientDbHelper(Context context) {
        super(context, BakingDatabaseContract._DATABASE_NAME, null, BakingDatabaseContract
                ._DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate ");
        final String SQL_CREATE_INGREDIENT_TABLE = "CREATE TABLE " +
                IngredientContract.IngredientsEntry.TABLE_NAME + " (" +
                IngredientContract.IngredientsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                IngredientContract.IngredientsEntry.COLUMN_BAKING_ID + " INTEGER NOT NULL, " +
                IngredientContract.IngredientsEntry.COLUMN_INGREDIENT + " TEXT NOT NULL," +
                IngredientContract.IngredientsEntry.COLUMN_MEASURE + " TEXT ," +
                IngredientContract.IngredientsEntry.COLUMN_QUANTITY + " INTEGER," +
                IngredientContract.IngredientsEntry.COLUMN_DTA_CRIACAO + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        Log.d(TAG, "query -- " + SQL_CREATE_INGREDIENT_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_INGREDIENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: implementar update table
        Log.d(TAG, "onUpgrade ");
        db.execSQL("DROP TABLE IF EXISTS " + IngredientContract.IngredientsEntry.TABLE_NAME);
        onCreate(db);
    }
}
