package com.hover.iot.android;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

/**
 * The application's starting activity.
 */
public class StartActivity extends AppCompatActivity {

    /**
     * Called when the activity is created.
     * If the user is authenticated, start the MainActivity, otherwise start the LoginActivity.
     *
     * @param savedInstanceState The saved instance state of the activity.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        splashScreen.setKeepOnScreenCondition(() -> true);

        HoverApplication mHomexApplication = HoverApplication.getInstance();

        Intent intent;

        if (mHomexApplication.isAuthenticated())
            intent = new Intent(this, MainActivity.class);
        else
            intent = new Intent(this, LoginActivity.class);

        startActivity(intent);
        finish();
    }
}