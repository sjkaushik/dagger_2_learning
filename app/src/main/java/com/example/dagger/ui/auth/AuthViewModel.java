package com.example.dagger.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.dagger.model.UserModel;
import com.example.dagger.network.auth.Authapi;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {

    private static final String TAG = "AuthViewModel";
    private Authapi authapi;

    @Inject
    public AuthViewModel(Authapi authapi) {
        this.authapi = authapi;

        authapi.getUser(1)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<UserModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(UserModel userModel) {

                        Log.d(TAG, "onNext: "+userModel.getEmail());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
