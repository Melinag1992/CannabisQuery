package com.example.c4q.cannabisproject.database;

import android.provider.BaseColumns;

/**
 * Created by amirahoxendine on 2/3/18.
 */

public class CannabisDBContract {
    private CannabisDBContract(){

    }

    public class CannabisEntry implements BaseColumns{
        public static final String TABLE_NAME = "cannabis_strains";
        public static final String COLUMN_NAME_OCPC = "ocpc";
        public static final String COLUMN_NAME_IMAGE = "image";
        public static final String COLUMN_NAME_URL = "url";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String _STATUS_FAV = "fav";
        public static final String _STATUS_WISH = "wishlist";
        public static final String _ID = "_id";

    }
}
