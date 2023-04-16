package com.hover.iot.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.material.textfield.TextInputLayout;
import com.hover.iot.android.models.HttpResponseListener;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * An activity that allows users to login the app.
 */
public class LoginActivity extends AppCompatActivity {
    /**
     * The input field for the user's username.
     */
    private TextInputLayout mUsernameInput;
    /**
     * The input field for the user's password.
     */
    private TextInputLayout mPasswordInput;

    /**
     * The progress bar that is used to display the progress during login.
     */
    private RelativeLayout mLoginProgressBar;

    /**
     * The button that allows users to navigate to the login screen.
     */
    private Button mLoginBtn;

    /**
     * The button that allows users to navigate to the login screen.
     */
    private Button mRegisterButton;

    /**
     * The application class.
     */
    private HoverApplication mHoverApplication;

    /**
     * Called when the activity is created. Sets the layout and initializes UI components.
     *
     * @param savedInstanceState A Bundle containing saved state information, if any.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mHoverApplication = HoverApplication.getInstance();

        mLoginBtn = findViewById(R.id.login_button);
        mUsernameInput = findViewById(R.id.login_username);
        mPasswordInput = findViewById(R.id.login_password);
        mLoginProgressBar = findViewById(R.id.logic_progress_bar);
        mLoginProgressBar.setVisibility(View.INVISIBLE);
        mRegisterButton = findViewById(R.id.goto_register_btn);

        mRegisterButton.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });

        mLoginBtn.setOnClickListener(v -> onLogin(
                Objects.requireNonNull(mUsernameInput.getEditText()).getText().toString().trim(),
                Objects.requireNonNull(mPasswordInput.getEditText()).getText().toString().trim()));

        onSetAnimations();
        onUpdateUI();
    }

    /**
     * Validates the username input field.
     *
     * @return a boolean indicating whether the username input field is valid or not.
     */
    private @NotNull Boolean isValidateUsername() {
        String val = Objects.requireNonNull(mUsernameInput.getEditText()).getText().toString();
        if (val.isEmpty()) {
            mUsernameInput.setError("Field Is Required");
            return false;
        } else {
            mUsernameInput.setError(null);
            mUsernameInput.setErrorEnabled(false);
            return true;
        }
    }

    /**
     * Validates the password input field.
     *
     * @return a boolean indicating whether the password input field is valid or not.
     */
    private boolean isValidPassword() {
        String val = Objects.requireNonNull(mPasswordInput.getEditText()).getText().toString();
        if (val.isEmpty()) {
            mPasswordInput.setError("Field Is Required");
            return false;
        } else {
            mPasswordInput.setError(null);
            mPasswordInput.setErrorEnabled(false);
            return true;
        }
    }

    /**
     * Animates the login view by translating them on the X-axis and increasing
     * their alpha value. The animations start with a delay to create a cascading effect.
     */
    private void onSetAnimations() {
        float v = 0;
        mUsernameInput.setTranslationX(800);
        mPasswordInput.setTranslationX(800);
        mLoginBtn.setTranslationX(800);
        mRegisterButton.setTranslationX(800);

        mUsernameInput.setAlpha(v);
        mPasswordInput.setAlpha(v);
        mLoginBtn.setAlpha(v);
        mRegisterButton.setAlpha(v);

        mUsernameInput.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        mPasswordInput.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        mLoginBtn.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        mRegisterButton.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
    }


    /**
     * Updates the UI by adding TextChangedListeners to the input fields and clearing any previous error messages.
     */
    private void onUpdateUI() {
        Objects.requireNonNull(mUsernameInput.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                clearInputErrors(mUsernameInput);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not used
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not used
            }
        });


        Objects.requireNonNull(mPasswordInput.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                clearInputErrors(mPasswordInput);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Not used
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not used
            }
        });
    }

    /**
     * Clears the error message and disables error state for the given TextInputLayout.
     *
     * @param inputLayout The TextInputLayout to clear the error for.
     */
    private void clearInputErrors(@NonNull TextInputLayout inputLayout) {
        if (inputLayout.getError() != null) {
            inputLayout.setError(null);
            inputLayout.setErrorEnabled(false);
        }
    }

    /**
     * Initiates the login process for the user.
     *
     * @param username The user's username.
     * @param password The user's password.
     */
    private void onLogin(String username, String password) {
        mLoginProgressBar.setVisibility(View.VISIBLE);
        mLoginBtn.setEnabled(false);

        if (!isValidateUsername() | !isValidPassword()) {
            mLoginProgressBar.setVisibility(View.INVISIBLE);
            mLoginBtn.setEnabled(true);
            return;
        }

        mHoverApplication.login(username, password, new HttpResponseListener() {
            @Override
            public void onSuccess(String data) {
                mLoginProgressBar.setVisibility(View.INVISIBLE);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                overridePendingTransition(R.anim.slide_out_right, android.R.anim.slide_in_left);
            }

            @Override
            public void onError(Exception e) {
                mLoginProgressBar.setVisibility(View.INVISIBLE);
                mLoginBtn.setEnabled(true);
            }
        });
    }
}