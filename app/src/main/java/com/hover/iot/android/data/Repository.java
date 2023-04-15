package com.hover.iot.android.data;

import android.app.Application;

import com.hover.iot.android.daos.UserDao;
import com.hover.iot.android.entities.User;

/**
 * A singleton class that provides access to the database data layer.
 */
public class Repository {

    /** User data access object. */
    private final UserDao mUserDao;

    /**
     * Initializes a new instance of the <see ref="Repository"/> class.
     * @param application The context of the application.
     */
    public Repository(Application application) {
        Datastore ds = Datastore.getInstance(application);
        mUserDao = ds.userDao();
    }

    /**
     * Inserts an object into a database table using a background thread.
     * @param entity The object to be inserted.
     * @param <T> The Entity type.
     */
    public <T> void add(T entity) {
        Datastore.dsExecutorService.execute(() -> {
            if (entity instanceof User) mUserDao.add((User) entity);
        });
    }

    /**
     * Gets the user from the database.
     * @param id The id of the user.
     * @return The user.
     */
    public User getUser(Integer id) {
        return mUserDao.get(id);
    }
}
