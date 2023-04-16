package com.hover.iot.android.services;

import androidx.annotation.NonNull;

import com.hover.iot.android.models.HttpResponseListener;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A class that provides a simple way to make HTTP requests using OkHttp.
 */
public class HttpService {
    /**
     * The singleton instance of the {@link HttpService} class.
     */
    private static HttpService mInstance;

    /**
     * An instance of {@link OkHttpClient} used to perform HTTP requests.
     */
    private final OkHttpClient mClient;

    /**
     * A constant that represents the JSON media type with UTF-8 charset.
     * @see MediaType
     */
    public static final MediaType APPLICATION_JSON = MediaType.get("application/json; charset=utf-8");

    /**
     * Private constructor to prevent direct instantiation of HttpService.
     * Creates a new OkHttpClient instance.
     */
    private HttpService() {
        mClient = new OkHttpClient();
    }

    /**
     * Returns singleton instance of the {@link HttpService} class.
     *
     * @return The instance of the {@link HttpService} class.
     */
    public static synchronized HttpService getInstance() {
        if (mInstance == null) {
            mInstance = new HttpService();
        }
        return mInstance;
    }

    /**
     * Registers a new user by sending a POST request to the register endpoint with the provided name, username, and password.
     * @param body The request body to include in the request.
     * @param listener The listener to be invoked when the request is completed.
     */
    public void login(@NonNull String url, @NonNull String body, final HttpResponseListener listener) {
        RequestBody requestBody = RequestBody.create(body, APPLICATION_JSON);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        executeRequest(request, listener);
    }

    /**
     * Registers a new user by sending a POST request to the register endpoint with the provided name, username, and password.
     * @param body The request body to include in the request.
     * @param listener The listener to be invoked when the request is completed.
     */
    public void register(@NonNull String url, @NonNull String body, final HttpResponseListener listener) {
        RequestBody requestBody = RequestBody.create(body, APPLICATION_JSON);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        executeRequest(request, listener);
    }

    /**
     * Sends an HTTP GET request to the specified URL.
     *
     * @param url The URL to send the request to.
     * @param listener The listener to be invoked when the request is completed.
     */
    public void get(@NonNull String url, @NonNull String token, final HttpResponseListener listener) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + token)
                .build();

        executeRequest(request, listener);
    }

    /**
     * Sends an HTTP GET request to the specified URL.
     *
     * @param url The URL to send the request to.
     * @param id The resource id.
     * @param listener The listener to be invoked when the request is completed.
     */
    public void get(@NonNull String url, @NonNull String id, @NonNull String token, final HttpResponseListener listener) {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + token)
                .build();

        executeRequest(request, listener);
    }

    /**
     * Sends an HTTP POST request to the specified URL with the given request body.
     *
     * @param url The URL to send the request to.
     * @param body The request body to include in the request.
     * @param listener The listener to be invoked when the request is completed.
     */
    public void post(@NonNull String url, @NonNull String token, @NonNull String body, final HttpResponseListener listener) {
        RequestBody requestBody = RequestBody.create(body, APPLICATION_JSON);

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + token)
                .post(requestBody)
                .build();

        executeRequest(request, listener);
    }

    /**
     * Sends an HTTP PUT request to the specified URL with the given request body and path parameter.
     *
     * @param url The URL to send the request to.
     * @param id The resource id.
     * @param token The authentication token.
     * @param body The request body to include in the request.
     * @param listener The listener to be invoked when the request is completed.
     */
    public void put(@NonNull String url, @NonNull String id, @NonNull String token, @NonNull String body, final HttpResponseListener listener) {
        RequestBody requestBody = RequestBody.create(body, APPLICATION_JSON);

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + token)
                .put(requestBody)
                .build();

        executeRequest(request, listener);
    }

    /**
     * Sends an HTTP DELETE request to the specified URL with the specified path parameter.
     *
     * @param url The URL to send the request to.
     * @param id The resource id.
     * @param token The authentication token.
     * @param listener The listener to be invoked when the request is completed.
     */
    public void delete(@NonNull String url, @NonNull String id, @NonNull String token, final HttpResponseListener listener) {
        Request request = new Request.Builder()
                .url(url.replace("{id}", id))
                .addHeader("Authorization", "Bearer " + token)
                .delete()
                .build();

        executeRequest(request, listener);
    }

    /**
     * Sends the specified request and invokes the callback when the request is completed.
     *
     * @param request The request to send.
     * @param listener The listener to be invoked when the request is completed.
     */
    private void executeRequest(Request request, final HttpResponseListener listener) {
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                listener.onError(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (!response.isSuccessful()) {
                    listener.onError(new Exception("Error response: " + response.code() + " - " +
                            response.message()));
                } else {
                    assert response.body() != null;
                    listener.onSuccess(response.body().string());
                }
                response.close();
            }
        });
    }

}

