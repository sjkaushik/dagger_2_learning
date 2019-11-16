package com.example.dagger.ui.auth;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.RequestManager;
import com.example.dagger.R;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {

    @Inject
    RequestManager requestManager;

    @Inject
    Drawable appLogo;

    @Inject
    AuthViewModel authViewModel;

    private TextView tvUserId;
    private Button loginBtn;
    private ProgressBar progressBar;

    private static final String TAG = "AuthActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        setLogo();
        tvUserId = findViewById(R.id.user_id_input);
        loginBtn = (findViewById(R.id.login_button));
        progressBar = findViewById(R.id.progress_bar);

        loginBtn.setOnClickListener(v -> {

            if (TextUtils.isEmpty(tvUserId.getText().toString()))
                return;

            authViewModel.authenticateWithId(Integer.parseInt(tvUserId.getText().toString()));
        });

        subscribeObservable();

    }

    private void subscribeObservable() {


        authViewModel.observerUser().observe(this, userModelAuthResource -> {

            if (userModelAuthResource != null) {


                switch (userModelAuthResource.status) {

                    case LOADING: {
                        showProgressBar(true);
                    }
                    break;

                    case ERROR: {
                        showProgressBar(false);
                        Toast.makeText(this, "Enter number between 1 and 10", Toast.LENGTH_SHORT).show();
                    }
                    break;

                    case AUTHENTICATED: {
                        showProgressBar(false);
                        Log.d(TAG, "subscribeObservable: " + userModelAuthResource.data.getUsername());
                    }
                    break;

                    case NOT_AUTHENTICATED: {
                        showProgressBar(false);

                    }
                    break;
                }
            }

        });

    }

    private void showProgressBar(Boolean isVisible) {

        if (isVisible)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);

    }

    private void setLogo() {
        requestManager.load(appLogo).into((ImageView) findViewById(R.id.login_logo));
    }
}
