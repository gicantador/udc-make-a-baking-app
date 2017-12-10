package com.pgcn.udcmakeabaking.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Giselle on 10/12/2017.
 */

public class IngredientContract extends BakingDatabaseContract {

    public static final String PATH_INGREDIENTS = "ingredients";

    public static final class IngredientsEntry implements BaseColumns {

        public static final Uri CONTENT_URI = _BASE_CONTENT_URI.buildUpon().appendPath(PATH_INGREDIENTS).build();

        public static final String TABLE_NAME = "bk_ingredients";

        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_BAKING_ID = "baking_id";
        public static final String COLUMN_MEASURE = "measure";
        public static final String COLUMN_INGREDIENT = "ingredient";
        public static final String COLUMN_DTA_CRIACAO = "created_at";

    }
}
