package com.example.demo28marchapp.Database;

public class DbUtils {
    public static final String DATABASE_NAME = "fav.db";
    public static final int DB_VERSION =4;
    public static final String FAV_TABLE = "fav_tb";
    public interface AddFav {
        String KEYID = "fav_id";
        String KEYURL = "fav_url";
        String KEYDATE = "fav_date";
        String KEYCOUNT = "fav_count";

    }

}
