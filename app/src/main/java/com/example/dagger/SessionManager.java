package com.example.dagger;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.dagger.model.UserModel;
import com.example.dagger.ui.auth.AuthResource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {

    private static final String TAG = "SessionManager";

    private MediatorLiveData<AuthResource<UserModel>> catchedUser = new MediatorLiveData<>();

    @Inject
    public SessionManager() {
    }

    public void authenticateWithId(final LiveData<AuthResource<UserModel>> source) {

        if (catchedUser != null) {
            catchedUser.setValue(AuthResource.loading(null));

            catchedUser.addSource(source, userModelAuthResource -> {

                catchedUser.setValue(userModelAuthResource);
                catchedUser.removeSource(source);
            });
        }
    }

    public void logout() {
        Log.d(TAG, "logout: logging our...");
        catchedUser.setValue(AuthResource.logout());

    }

    public MediatorLiveData<AuthResource<UserModel>> getAuthenticatedUser() {
        return catchedUser;
    }
}
