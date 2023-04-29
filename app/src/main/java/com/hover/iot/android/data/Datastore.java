package com.hover.iot.android.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hover.iot.android.dao.UserDao;
import com.hover.iot.android.entity.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * An abstract class extending the RoomDatabase class that provides access to the database.
 */
@Database(entities = { User.class}, version = 1, exportSchema = false)
public abstract class Datastore extends RoomDatabase {

    /**
     * An executor service that is used to run database operations asynchronously on a
     * background thread.
     */
    public static final ExecutorService dsExecutorService = Executors.newFixedThreadPool(4);

    /**
     * Datastore singleton instance.
     */
    private static volatile Datastore INSTANCE;

    /**
     * Get the singleton instance of the Datastore.
     *
     * @param context The context of the application.
     * @return The singleton instance of the Datastore.
     */
    public static synchronized Datastore getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    Datastore.class, "hover").build();
        }

        return INSTANCE;
    }

    /**
     * Get the instance of the UserDao.
     *
     * @return The UserDao object.
     */
    public abstract UserDao userDao();
}
