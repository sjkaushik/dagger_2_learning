package com.example.dagger.ui.auth;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.example.dagger.R;
import com.example.dagger.network.auth.Authapi;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {

    @Inject
    RequestManager requestManager;

    @Inject
    Drawable appLogo;

    @Inject
    AuthViewModel authViewModel;

    private static final String TAG = "AuthActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        setLogo();

    }

    private void setLogo() {
        requestManager.load(appLogo).into((ImageView) findViewById(R.id.login_logo));
    }
}
