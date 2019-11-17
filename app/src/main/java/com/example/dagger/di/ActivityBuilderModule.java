package com.example.dagger.di;

import com.example.dagger.di.auth.AuthModule;
import com.example.dagger.ui.auth.AuthActivity;
import com.example.dagger.ui.main.MainActivity;
import com.example.dagger.ui.main.post.PostFragment;
import com.example.dagger.ui.main.profile.ProfileFragment;

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

    @ContributesAndroidInjector(modules = {
            AuthModule.class
    })
    abstract MainActivity mainActivityContributes();

    @ContributesAndroidInjector
    abstract ProfileFragment profileFragmentContributes();

    @ContributesAndroidInjector(modules = {
            AuthModule.class
    })
    abstract PostFragment postFragmentContributes();
}
