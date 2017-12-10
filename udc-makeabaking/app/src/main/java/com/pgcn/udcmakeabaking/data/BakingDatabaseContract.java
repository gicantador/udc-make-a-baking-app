package com.pgcn.udcmakeabaking.data;

import android.net.Uri;

/**
 * Created by Giselle on 10/12/2017.
 */

public class BakingDatabaseContract {

    public static final String _DATABASE_NAME = "baking.db";
    public static final int _DATABASE_VERSION = 1;
    public static final String _AUTHORITY = "com.pgcn.udcmakeabaking.data";
    public static final Uri _BASE_CONTENT_URI = Uri.parse("content://" + _AUTHORITY);

}
