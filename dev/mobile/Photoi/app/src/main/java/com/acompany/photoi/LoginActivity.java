package com.acompany.photoi;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;


import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

/**
 * A login screen that offers login via username/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_USERSDB = 0;


    // UI references.
    private EditText mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;



    public class PasswordMatchResponseReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP =
                "com.acompany.photoi.intent.action.USER_LOGIN_MESSAGE_PROCESSED";

        @Override
        public void onReceive(Context context, Intent intent) {

            showProgress(false);

            Boolean passwordMatch = intent.getBooleanExtra(UsersService.PASSWORD_MATCH_PARAM,false);
            String username = intent.getStringExtra(UsersService.USERNAME);


            if (passwordMatch) {

                // starts the product search activity
                Intent productSearchIntent = new Intent(context, ProductSearchActivity.class);
                productSearchIntent.putExtra(UsersService.USERNAME,username);
                startActivity(productSearchIntent);

            } else {

                mUsernameView.setText("");
                mPasswordView.setText("");

                mUsernameView.setError("Credentials mismatch...");

            }

        }
    }

    public class SessionFinishedResponseReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP =
                "com.acompany.photoi.intent.action.USER_LOGOUT_PROCESSED";

        @Override
        public void onReceive(Context context, Intent intent) {

            // starts the product search activity
            Intent loginIntent = new Intent(context, LoginActivity.class);
            startActivity(loginIntent);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        PhotoiUserSessionManager session = new PhotoiUserSessionManager(this);

        String username = session.getValidSessionUsername();

        if (username.compareTo(PhotoiUserSessionManager.NOT_VALID_TOKEN) == 0) {

            setContentView(R.layout.activity_login);


            /*
            LISTENERS REGISTRATION
             */
            // Register the password match event callback
            IntentFilter filter1 = new IntentFilter(PasswordMatchResponseReceiver.ACTION_RESP);
            filter1.addCategory(Intent.CATEGORY_DEFAULT);
            PasswordMatchResponseReceiver receiver1 = new PasswordMatchResponseReceiver();
            registerReceiver(receiver1, filter1);


            // Register the user logout event callback
            IntentFilter filter2 = new IntentFilter(SessionFinishedResponseReceiver.ACTION_RESP);
            filter2.addCategory(Intent.CATEGORY_DEFAULT);
            SessionFinishedResponseReceiver receiver2 = new SessionFinishedResponseReceiver();
            registerReceiver(receiver2, filter2);



            // Set up the login form.
            mUsernameView = (EditText) findViewById(R.id.username);


            mPasswordView = (EditText) findViewById(R.id.password);
            mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                    if (id == R.id.login || id == EditorInfo.IME_NULL) {
                        attemptLogin();
                        return true;
                    }
                    return false;
                }
            });

            Button mUsernameSignInButton = (Button) findViewById(R.id.username_sign_in_button);
            mUsernameSignInButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    attemptLogin();
                }
            });

            mLoginFormView = findViewById(R.id.login_form);
            mProgressView = findViewById(R.id.login_progress);

        } else {


            // starts the product search activity
            Intent productSearchIntent = new Intent(this, ProductSearchActivity.class);
            productSearchIntent.putExtra(UsersService.USERNAME,username);
            startActivity(productSearchIntent);


        }


    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid username, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mUsernameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid username
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        } else if (!isUsernameValid(username)) {
            mUsernameView.setError(getString(R.string.error_invalid_username));
            focusView = mUsernameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {

            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);

            UsersService.startActionLogin(this, mUsernameView.getText().toString(),mPasswordView.getText().toString());

        }
    }

    private boolean isUsernameValid(String username) {

        return !(username.contains(" ") || username.contains(","));
    }

    private boolean isPasswordValid(String password) {

        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}

