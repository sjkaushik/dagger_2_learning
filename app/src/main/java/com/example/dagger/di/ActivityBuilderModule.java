package com.example.dagger.di;

import com.example.dagger.di.auth.AuthModule;
import com.example.dagger.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
            modules = {
                    AuthModule.class,
            }
    )
    abstract AuthActivity authActivityContributes();

}
