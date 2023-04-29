package com.hover.iot.android.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.hover.iot.android.entity.User;

/**
 * User data access object interface.
 */
@Dao
public interface UserDao extends BaseDao<User> {

    /**
     * Gets a user by id.
     * @param id The id of the user to retrieve.
     * @return A user.
     */
    @Query("SELECT * FROM tbl_user WHERE id = :id")
    User get(int id);
}
