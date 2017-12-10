package com.pgcn.udcmakeabaking.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Giselle on 10/12/2017.
 */

public class BakingContract extends BakingDatabaseContract
{

    public static final String PATH_BAKING = "baking";

    public static final class BakingEntry implements BaseColumns {

        public static final Uri CONTENT_URI = _BASE_CONTENT_URI.buildUpon().appendPath(PATH_BAKING).build();

        public static final String TABLE_NAME = "bk_baking";

        public static final String COLUMN_BAKING_ID = "baking_id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SERVINGS = "servings";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_DTA_CRIACAO = "created_at";


    }
}
