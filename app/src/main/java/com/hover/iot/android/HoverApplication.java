package com.hover.iot.android;

import android.app.Application;

import androidx.annotation.NonNull;

import com.hover.iot.android.models.HttpResponseListener;
import com.hover.iot.android.services.HttpService;
import com.hover.iot.android.util.PropertyManager;
import com.hover.iot.android.util.constants.ApiRoutes;
import com.hover.iot.android.util.constants.PropertyKeys;

import org.jetbrains.annotations.Contract;
import org.json.JSONObject;

import java.io.IOException;

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
     * A service used to make http requests.
     */
    private HttpService mHttpService;

    /**
     * The base backend server url;
     */
    private String SERVER_BASE_URL;

    /**
     * Returns singleton instance of the {@link HoverApplication} class.
     *
     * @return The instance of the {@link HoverApplication} class.
     */
    public static synchronized HoverApplication getInstance() {
        return mInstance;
    }

    /**
     * Called when the activity is created.
     */
    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        mHttpService = HttpService.getInstance();

        try {
            PropertyManager mPropertyManager = new PropertyManager(this);
            SERVER_BASE_URL = mPropertyManager.getProperty(PropertyKeys.SERVER_BASE_URL_KEY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Send a request to the server to register the user.
     * @param name The user's name.
     * @param username The user's username.
     * @param password The user's password.
     * @param response The HttpResponseListener to use for handling the response.
     */
    public void register(String name, String username, String password,
                         HttpResponseListener response) {
        try {

            JSONObject body = new JSONObject();

            body.put("name", name);
            body.put("username", username);
            body.put("password", password);

            mHttpService.register(createUrl(ApiRoutes.Register), body.toString(), response);

        } catch (Exception e) {
            response.onError(e);
        }
    }

    /**
     * Creates an absolute URL by concatenating the server base URL with a relative URL.
     * @param relativeUrl The relative URL to be concatenated.
     * @return The absolute URL.
     */
    @NonNull
    @Contract(pure = true)
    private String createUrl(@NonNull String relativeUrl) {
        return SERVER_BASE_URL + relativeUrl;
    }
}
