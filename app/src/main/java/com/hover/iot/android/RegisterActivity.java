package com.hover.iot.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.android.material.textfield.TextInputLayout;
import com.hover.iot.android.models.HttpResponseListener;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * An activity that allows users to register for an account in the app.
 */
public class RegisterActivity extends AppCompatActivity {

    /**
     * The input field for the user's full name.
     */
    private TextInputLayout mFullNameInput;

    /**
     * The input field for the user's username.
     */
    private TextInputLayout mUsernameInput;
    /**
     * The input field for the user's password.
     */
    private TextInputLayout mPasswordInput;

    /**
     * The layout that contains the progress bar during registration.
     */
    private RelativeLayout mRegisterProgressLayout;

    /**
     * The progress bar that is used to display the progress during registration.
     */
    private ProgressBar mRegisterProgressBar;

    /**
     * The button that initiates the registration process.
     */
    private Button mRegisterButton;

    /**
     * The button that allows users to navigate to the login screen.
     */
    private Button mLoginButton;

    /**
     * The image view that is used display the "done" checkmark after registration is complete.
     */
    private ImageView mProgressDoneImageView;

    /**
     * The vector drawable that is used to display the "done" checkmark after registration is complete.
     */
    private AnimatedVectorDrawable mDoneVectorDrawable;

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
        setContentView(R.layout.activity_register);

        mHoverApplication = HoverApplication.getInstance();

        mFullNameInput = findViewById(R.id.register_full_name);
        mUsernameInput = findViewById(R.id.register_username);
        mPasswordInput = findViewById(R.id.register_password);
        mRegisterButton = findViewById(R.id.register_btn);
        mLoginButton = findViewById(R.id.goto_login_btn);
        mProgressDoneImageView = findViewById(R.id.register_progress_done_image_view);
        mRegisterProgressBar = findViewById(R.id.register_progress_bar);
        mRegisterProgressLayout = findViewById(R.id.register_progress_layout);
        mRegisterProgressLayout.setVisibility(View.INVISIBLE);
        Drawable drawable = mProgressDoneImageView.getDrawable();
        mDoneVectorDrawable = (AnimatedVectorDrawable) drawable;

        RelativeLayout backBtn = findViewById(R.id.register_back_btn);

        backBtn.setOnClickListener(v -> finish());

        mDoneVectorDrawable.registerAnimationCallback(new Animatable2.AnimationCallback() {
            @Override
            public void onAnimationEnd(Drawable drawable) {
                super.onAnimationEnd(drawable);
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mProgressDoneImageView.setVisibility(View.INVISIBLE);

        mLoginButton.setOnClickListener(v -> finish());

        mRegisterButton.setOnClickListener(v -> {

            if (!isValidName() || !isValidUsername() || !isValidPassword()) {
                return;
            }

            String name = Objects.requireNonNull(mFullNameInput.getEditText()).getText().toString();
            String username = Objects.requireNonNull(mUsernameInput.getEditText()).getText().toString();
            String password = Objects.requireNonNull(mPasswordInput.getEditText()).getText().toString();

            onRegistration(name, username, password);
        });

        onSetAnimations();
        onUpdateUI();
    }

    /**
     * Validates the full name input field.
     *
     * @return a boolean indicating whether the Full Name input field is valid or not.
     */
    private boolean isValidName() {
        String val = Objects.requireNonNull(mFullNameInput.getEditText()).getText().toString();

        if (val.isEmpty()) {
            mFullNameInput.setError("Field Is Required");
            return false;
        } else {
            mFullNameInput.setError(null);
            mFullNameInput.setErrorEnabled(false);
            return true;
        }
    }

    /**
     * Validates the username input field.
     *
     * @return a boolean indicating whether the username input field is valid or not.
     */
    private boolean isValidUsername() {
        String val = Objects.requireNonNull(mUsernameInput.getEditText()).getText().toString().trim();
        String valPat = "^\\w{3,}$";

        if (val.isEmpty()) {
            mUsernameInput.setError("Field is required");
            return false;
        } else if (!val.matches(valPat)) {
            mUsernameInput.setError("Username should contain at least 3 characters, only letters, numbers, dots, underscores, and hyphens are allowed");
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
        String valPat = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).+$";

        if (val.isEmpty()) {
            return false;
        } else if (!val.matches(valPat)) {
            mPasswordInput.setError("Invalid Password Req Format S@mpl3");
            return false;
        }
        mPasswordInput.setError(null);
        mPasswordInput.setErrorEnabled(false);
        return true;
    }
    /**
     * Animates the login and register views by translating them on the X-axis and increasing their alpha value.
     * The animations start with a delay to create a cascading effect.
     */
    private void onSetAnimations() {
        float v = 0;

        mFullNameInput.setTranslationX(800);
        mUsernameInput.setTranslationX(800);
        mPasswordInput.setTranslationX(800);
        mRegisterButton.setTranslationX(800);
        mLoginButton.setTranslationX(800);

        mFullNameInput.setAlpha(v);
        mUsernameInput.setAlpha(v);
        mPasswordInput.setAlpha(v);
        mRegisterButton.setAlpha(v);
        mLoginButton.setAlpha(v);

        mFullNameInput.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        mUsernameInput.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        mPasswordInput.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        mRegisterButton.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        mLoginButton.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
    }

    /**
     * Updates the UI by adding TextChangedListeners to the input fields and clearing any previous error messages.
     */
    private void onUpdateUI() {
        Objects.requireNonNull(mFullNameInput.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                clearInputErrors(mFullNameInput);
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
     * @param inputLayout The TextInputLayout to clear the error for.
     */
    private void clearInputErrors(@NonNull TextInputLayout inputLayout) {
        if (inputLayout.getError() != null) {
            inputLayout.setError(null);
            inputLayout.setErrorEnabled(false);
        }
    }

    /**
     * Initiates the registration process for the user.
     *
     * @param name     The user's full name.
     * @param username The user's username.
     * @param password The user's password.
     */
    private void onRegistration(String name, String username, String password) {
        mRegisterProgressLayout.setVisibility(View.VISIBLE);
        mRegisterButton.setEnabled(false);

        mHoverApplication.register(name, username, password, new HttpResponseListener() {
            @Override
            public void onSuccess(String data) {
                mRegisterProgressBar.setVisibility(View.INVISIBLE);
                mProgressDoneImageView.setVisibility(View.VISIBLE);
                mDoneVectorDrawable.start();
            }

            @Override
            public void onError(Exception exception) {
                mRegisterProgressLayout.setVisibility(View.INVISIBLE);
                mRegisterButton.setEnabled(false);
            }
        });
    }
}