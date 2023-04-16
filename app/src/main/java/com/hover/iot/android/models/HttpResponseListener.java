package com.hover.iot.android.models;

/**
 * An interface for handling HTTP responses.
 */
public interface HttpResponseListener {
    /**
     * Called when the HTTP request is successful.
     *
     * @param data The data returned from the http request.
     */
    void onSuccess(String data);

    /**
     * Called when an error occurs during the HTTP request.
     *
     * @param exception The exception that occurred.
     */
    void onError(Exception exception);
}