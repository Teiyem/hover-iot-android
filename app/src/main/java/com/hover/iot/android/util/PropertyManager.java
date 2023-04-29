package com.hover.iot.android.util;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.annotation.NonNull;

import com.hover.iot.android.service.HttpService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * A utility class for managing properties.
 */
public class PropertyManager  {

    /**
     * The name of the properties file.
     */
    private static final String PROPERTIES_FILE_NAME = "app.properties";

    /**
     * The properties loaded from the file.
     */
    private final Properties properties;

    /**
     * Initializes a new instance of the {@link PropertyManager} class with the specified context.
     *
     * @param context The context used to load the properties file.
     * @throws IOException If an I/O error occurs while loading the file.
     */
    public PropertyManager(@NonNull Context context) throws IOException {
        properties = new Properties();
        AssetManager assetManager = context.getAssets();

        try (InputStream inputStream = assetManager.open(PROPERTIES_FILE_NAME )) {
            properties.load(inputStream);
        }
    }

    /**
     * Returns the value of the property with the given key.
     *
     * @param key The key of the property to retrieve.
     * @return The value of the property, or null if it does not exist.
     */
    public String getProperty(@NonNull String key) {
        return properties.getProperty(key);
    }
}
