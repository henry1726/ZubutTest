package com.example.zubuttest.sys.util;

import android.Manifest;

public final class Constants {

    public static class Db {

        public static final String DB_NAME = "Adress_db";

    }

    public static class Permissions {

        public static final int REQUEST_CODE_GPS_ENABLE = 1;

        public static String[] PERMISSIONS = new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
        };

        public static final int REQUEST_CODE_PERMISSIONS = 300;

    }
}
