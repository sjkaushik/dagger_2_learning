package com.example.dagger.di.auth;


import com.example.dagger.model.UserModel;
import com.example.dagger.network.auth.Authapi;
import com.example.dagger.ui.main.profile.PostsRecyclerAdapter;


import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class AuthModule {

    @AuthScope
    @Provides
    @Named("user_auth")
    static UserModel providesUser() {
        return new UserModel();
    }

    @Provides
    static PostsRecyclerAdapter providesAdapter() {
        return new PostsRecyclerAdapter();
    }


    @Provides
    static Authapi providesAuthAPI(Retrofit retrofit) {
        return retrofit.create(Authapi.class);
    }
}
