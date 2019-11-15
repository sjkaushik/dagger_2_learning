package com.example.dagger.network.auth;

import com.example.dagger.model.UserModel;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Authapi {

    @GET("users/{id}")
    Flowable<UserModel> getUser(@Path("id") int id);
}
