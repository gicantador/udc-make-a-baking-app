package com.pgcn.udcmakeabaking.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import static com.pgcn.udcmakeabaking.data.IngredientContract.IngredientsEntry.TABLE_NAME;

/**
 * Created by Giselle on 10/12/2017.
 */

public class IngredientsContentProvider extends ContentProvider {

    private static final String TAG = IngredientsContentProvider.class.getSimpleName();
    private static final int INGREDIENT = 100;
    private static final int INGREDIENT_WITH_ID = 101;
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private IngredientDbHelper mIngredientsDbHelper;

    private static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(IngredientContract._AUTHORITY, IngredientContract.PATH_INGREDIENTS,
                INGREDIENT);
        uriMatcher.addURI(IngredientContract._AUTHORITY, IngredientContract.PATH_INGREDIENTS +
                "/#", INGREDIENT_WITH_ID);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        Log.d(TAG, " onCreate()");
        Context context = getContext();
        mIngredientsDbHelper = new IngredientDbHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Log.d(TAG, " query()");
        final SQLiteDatabase db = mIngredientsDbHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);
        Cursor retCursor;

        switch (match) {
            // Query for the tasks directory
            case INGREDIENT:
                retCursor = db.query(TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.d(TAG, " getType()");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        Log.d(TAG, " insert()");
        final SQLiteDatabase db = mIngredientsDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        Uri returnUri;

        switch (match) {
            case INGREDIENT:
                long id = db.insert(TABLE_NAME, null, values);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(IngredientContract.IngredientsEntry.CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, " delete()");
        final SQLiteDatabase db = mIngredientsDbHelper.getWritableDatabase();

        int match = sUriMatcher.match(uri);
        int tasksDeleted;
        switch (match) {
            case INGREDIENT_WITH_ID:
                String id = uri.getPathSegments().get(1);
                tasksDeleted = db.delete(TABLE_NAME, "_id=?", new String[]{id});
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (tasksDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return tasksDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, " update()");
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
