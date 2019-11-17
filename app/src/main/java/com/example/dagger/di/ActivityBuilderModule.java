package com.example.dagger.di;

import com.example.dagger.di.auth.AuthModule;
import com.example.dagger.ui.auth.AuthActivity;
import com.example.dagger.ui.main.MainActivity;
import com.example.dagger.ui.main.fragment.ProfileFragment;

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

    @ContributesAndroidInjector
    abstract MainActivity mainActivityContributes();

    @ContributesAndroidInjector
    abstract ProfileFragment profileFragmentContributes();
}
