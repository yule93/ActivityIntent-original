package com.jyheo.activityintent;

import android.provider.BaseColumns;

/**
 * Created by HOME on 2017-12-14.
 */
public final class UserContract {
    public static final String DB_NAME="Safecity.db";
    public static final int DATABASE_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private UserContract() {}
    /* Inner class that defines the table contents */
    public static class Users implements BaseColumns {
        public static final String TABLE_NAME="SafeCity";
        public static final String KEY_NAME = "Name";
        public static final String KEY_PLACE = "PlaceName";
        public static final String KEY_MAXIMUM_ADMIT = "AdmitNumber";
        public static final String KEY_PHONE_NUMBER = "PhoneNumber";
        public static final String KEY_ADMIN_NUMBERING = "AdminNumber";
        public static final String KEY_ADMIN_NAME = "AdminName";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                KEY_NAME + TEXT_TYPE + COMMA_SEP +
                KEY_PLACE + TEXT_TYPE +  " )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}