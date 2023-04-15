package com.hover.iot.android.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import java.util.List;

/**
 * Generic data access object interface.
 * @param <T> The entity type.
 */
@Dao
public interface BaseDao<T> {

    /**
     * Insert an object in the database.
     * @param entity The object to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(T entity);

    /**
     * Insert an list of objects in the database.
     * @param entities The list objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(List<T> entities);

    /**
     * Update an object from the database.
     * @param entity The object to be updated.
     * @return The number of rows affected by the update.
     */
    @Update
    int update(T entity);

    /**
     * Delete an entity from the database.
     * @param entity The entity to be deleted.
     * @return The number of rows deleted.
     */
    @Delete
    int delete(T entity);

    /**
     * Delete an list of entities from the database.
     * @param entities The list objects to be deleted.
     * @return The number of rows deleted.
     */
    @Delete
    int delete(List<T> entities);
}
