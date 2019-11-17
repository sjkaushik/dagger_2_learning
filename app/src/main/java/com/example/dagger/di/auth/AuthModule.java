package com.example.dagger.di.auth;


import com.example.dagger.network.auth.Authapi;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class AuthModule {

    @Provides
    static Authapi providesAuthAPI(Retrofit retrofit){
        return retrofit.create(Authapi.class);
    }
}
