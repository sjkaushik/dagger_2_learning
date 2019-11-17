package com.example.dagger;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;

import com.example.dagger.ui.auth.AuthActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    private static final String TAG = "BaseActivity";

    @Inject
    public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObservers();
    }

    private void subscribeObservers() {

        sessionManager.getAuthenticatedUser().observe(this, userModelAuthResource -> {

            if (userModelAuthResource != null) {


                switch (userModelAuthResource.status) {

                    case LOADING: {

                    }
                    break;

                    case ERROR: {

                    }
                    break;

                    case AUTHENTICATED: {

                    }
                    break;

                    case NOT_AUTHENTICATED: {
                        navLoginScreen();

                    }
                    break;
                }
            }
        });
    }

    private void navLoginScreen() {
        startActivity(new Intent(BaseActivity.this, AuthActivity.class));
        finish();
    }
}
