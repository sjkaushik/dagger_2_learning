package com.example.dagger.ui.auth;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dagger.SessionManager;
import com.example.dagger.model.UserModel;
import com.example.dagger.network.auth.Authapi;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";
    private Authapi authapi;
    private SessionManager sessionManager;

    @Inject
    public AuthViewModel(Authapi authapi,SessionManager sessionManager) {
        this.authapi = authapi;
        this.sessionManager = sessionManager;
    }

    public void authenticateWithId(int id) {

        Log.d(TAG, "authenticateWithId: Attempting login");

        sessionManager.authenticateWithId(queryId(id));

    }

    public LiveData<AuthResource<UserModel>> queryId(int id) {

        return LiveDataReactiveStreams.fromPublisher(
                authapi.getUser(id)

                        .onErrorReturn(throwable -> {
                            UserModel errorUserModel = new UserModel();
                            errorUserModel.setId(-1);
                            return errorUserModel;
                        })

                        .map((Function<UserModel, AuthResource<UserModel>>) userModel -> {
                            if (userModel.getId() == -1) {
                                return AuthResource.error("could not authenticated", null);
                            }

                            return AuthResource.authenticated(userModel);
                        })
                        .subscribeOn(Schedulers.io())
        );
    }


    public MediatorLiveData<AuthResource<UserModel>> setObserverState() {
        return sessionManager.getAuthenticatedUser();
    }
}
