package com.example.dagger.ui.auth;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.dagger.model.UserModel;
import com.example.dagger.network.auth.Authapi;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";
    private Authapi authapi;

    private MediatorLiveData<AuthResource<UserModel>> authUser = new MediatorLiveData();

    @Inject
    public AuthViewModel(Authapi authapi) {
        this.authapi = authapi;

    }

    public void authenticateWithId(int id) {

        authUser.setValue(AuthResource.loading(null));

        final LiveData<AuthResource<UserModel>> source = LiveDataReactiveStreams.fromPublisher(
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

        authUser.addSource(source, userModel -> {
            authUser.setValue(userModel);
            authUser.removeSource(source);
        });
    }


    public MediatorLiveData<AuthResource<UserModel>> observerUser() {
        return authUser;
    }
}
