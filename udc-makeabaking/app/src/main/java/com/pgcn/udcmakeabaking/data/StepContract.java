package com.pgcn.udcmakeabaking.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Giselle on 10/12/2017.
 */

public class StepContract extends BakingDatabaseContract {

    public static final String PATH_STEPS = "steps";

    public static final class StepsEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                _BASE_CONTENT_URI.buildUpon().appendPath(PATH_STEPS).build();

        public static final String TABLE_NAME = "bk_steps";

        public static final String COLUMN_BAKING_ID = "baking_id";
        public static final String COLUMN_SORT_ID = "sort_id";
        public static final String COLUMN_SHORT_DESCRIPTION = "shortDescription";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_VIDEO_URL = "videoURL";
        public static final String COLUMN_THUMBNAIL_URL = "thumbnailURL";
        public static final String COLUMN_DTA_CRIACAO = "created_at";

    }
}
