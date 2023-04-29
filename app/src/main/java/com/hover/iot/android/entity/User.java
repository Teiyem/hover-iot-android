package com.hover.iot.android.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * User model class.
 */
@Entity(tableName = "tbl_user")
public class User {

    /**
     * The user's id.
     */
    @PrimaryKey
    @ColumnInfo(name = "id")
    private final int id;

    /**
     * The user's name.
     */
    @NonNull
    @ColumnInfo(name = "name")
    private final String name;

    /**
     * The user's username.
     */
    @NonNull
    @ColumnInfo(name = "username")
    private final String username;

    /**
     * Initializes a new instance of the {@link User} class.
     * @param id The user's ID.
     * @param name The user's name.
     * @param username The user's username.
     */
    public User(int id, @NonNull String name, @NonNull String username) {
        this.id = id;
        this.name = name;
        this.username = username;
    }

    /**
     * Gets the user's ID.
     * @return The user's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the user's name.
     * @return The user's name.
     */
    @NonNull
    public String getName() {
        return name;
    }
    /**
     * Gets the user's surname.
     * @return The user's surname.
     */
    @NonNull
    public String getUsername() {
        return username;
    }
}
