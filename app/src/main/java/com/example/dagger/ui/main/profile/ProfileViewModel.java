package com.example.dagger.ui.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.dagger.SessionManager;
import com.example.dagger.model.UserModel;
import com.example.dagger.ui.auth.AuthResource;

import javax.inject.Inject;

public class ProfileViewModel {

    private static final String TAG = "ProfileViewModel";

    private final SessionManager sessionManager;

    @Inject
    public ProfileViewModel(SessionManager sessionManager) {

        this.sessionManager = sessionManager;

        Log.d(TAG, "ProfileViewModel: profile view model is ready to use...!");
    }

    public LiveData<AuthResource<UserModel>> getUserData(){

        return sessionManager.getAuthenticatedUser();
    }
}
