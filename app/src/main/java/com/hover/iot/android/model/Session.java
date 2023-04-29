package com.hover.iot.android.model;

/**
 * A representation of a user's session with the application.
 */
public class Session {

    /**
     * The user id.
     */
    private final String mUserId;

    /**
     * The access token.
     */
    private final String mAccessToken;

    /**
     * The refresh token.
     */
    private final String mRefreshToken;

    /**
     * The number of seconds until the access token expires.
     */
    private final long mExpiresIn;

    /**
     * Initializes a new instance of the {@link Session} class.
     *
     * @param userId       The user's id.
     * @param accessToken  The access token.
     * @param refreshToken The refresh token.
     * @param expiresIn    The number of seconds until the access token expires.
     */
    public Session(String userId, String accessToken, String refreshToken, long expiresIn) {
        mUserId = userId;
        mAccessToken = accessToken;
        mRefreshToken = refreshToken;
        mExpiresIn = expiresIn;
    }

    /**
     * Gets the access token.
     *
     * @return The access token.
     */
    public String getAccessToken() {
        return mAccessToken;
    }

    /**
     * Gets the refresh token.
     *
     * @return The refresh token.
     */
    public String getRefreshToken() {
        return mRefreshToken;
    }

    /**
     * Gets the access token's expires in time.
     *
     * @return The access token's expires in time.
     */
    public long getExpiresIn() {
        return mExpiresIn;
    }

    /**
     * Gets the user's id.
     *
     * @return The user's id.
     */
    public String getUserId() {
        return mUserId;
    }

    /**
     * Gets whether or not the token is expired.
     *
     * @return -true Access token is expired.
     * -false Access token is not expired.
     */
    public boolean isExpired() {
        return expiresIn() < 0;
    }

    /**
     * Gets the number of seconds util the token expires.
     *
     * @return The time in seconds until the token expires.
     */
    private long expiresIn() {
        return mExpiresIn - System.currentTimeMillis() / 1000;
    }
}
