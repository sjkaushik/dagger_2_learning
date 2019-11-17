package com.example.dagger.ui.main.post;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dagger.R;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostFragment extends DaggerFragment {

    private static final String TAG = "PostFragment";

    @Inject
    PostViewModel postViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        subscribeObserver();
    }

    private void subscribeObserver() {

        postViewModel.observePosts().removeObservers(getViewLifecycleOwner());

        postViewModel.observePosts().observe(getViewLifecycleOwner(),observer ->{

            if(observer!=null){

                Log.d(TAG, "subscribeObserver: "+observer.data);
            }
        });
    }
}
