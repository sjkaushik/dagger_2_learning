package com.example.dagger.network.auth;

import com.example.dagger.model.PostModel;
import com.example.dagger.model.UserModel;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Authapi {

    @GET("users/{id}")
    Flowable<UserModel> getUser(@Path("id") int id);

    @GET("posts")
    Flowable<List<PostModel>> getUserPosts(@Query("userId") int id);


}
