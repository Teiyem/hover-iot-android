package com.hover.iot.android;

import android.app.Application;

import com.hover.iot.android.models.HttpResponseListener;
import com.hover.iot.android.models.RegisterRequest;

import org.json.JSONObject;

/**
 * A main application class that provides a central point for managing global application state and
 * resources.
 */
public class HoverApplication extends Application {

    /**
     * The singleton instance of the {@link HoverApplication} class.
     */
    private static HoverApplication mInstance;

    /**
     * Returns singleton instance of the {@link HoverApplication} class.
     *
     * @return The instance of the {@link HoverApplication} class.
     */
    public static synchronized HoverApplication getInstance() {
        return mInstance;
    }
}
