package com.example.dagger.di;

import android.app.Application;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.dagger.BuildConfig;
import com.example.dagger.R;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {


    @Provides
    static Retrofit provideRetrofit(OkHttpClient client){

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    static RequestOptions provideRequestOptions() {
        return RequestOptions.placeholderOf(R.drawable.white_background).error(R.drawable.white_background);
    }

    @Provides
    static OkHttpClient providesLoggingInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Provides
    static RequestManager provideRequestManager(Application application,RequestOptions requestOptions){
        return Glide.with(application).setDefaultRequestOptions(requestOptions);
    }

    @Provides
    static Drawable provideDrawable(Application application){
        return ContextCompat.getDrawable(application,R.drawable.logo);
    }

}
