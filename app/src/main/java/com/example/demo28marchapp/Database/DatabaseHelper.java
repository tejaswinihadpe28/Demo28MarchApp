package com.example.demo28marchapp.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {AddFavDao.class}, version = DbUtils.DB_VERSION, exportSchema = false)
public abstract class DatabaseHelper extends RoomDatabase {
    public abstract InterfaceQuery interfaceDao();

    private static DatabaseHelper INSTANCE;

    public static DatabaseHelper getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseHelper.class) {
                if (INSTANCE == null) {
                    //Log.i(TAG, "getDatabase: ");
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseHelper.class, DbUtils.DATABASE_NAME)
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this codelab.
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
//                            .addCallback(callback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
