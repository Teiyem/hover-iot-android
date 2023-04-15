package com.hover.iot.android.daos;

import androidx.room.Query;

import com.hover.iot.android.entities.User;

/**
 * User data access object interface.
 */
public interface UserDao extends BaseDao<User> {

    /**
     * Gets a user by id.
     * @param id The id of the user to retrieve.
     * @return A user.
     */
    @Query("SELECT * FROM user WHERE id = :id")
    User get(Integer id);
}
