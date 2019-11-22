package com.example.dagger.ui.main.post;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dagger.R;
import com.example.dagger.ui.main.profile.PostsRecyclerAdapter;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostFragment extends DaggerFragment {

    private static final String TAG = "PostFragment";
    private RecyclerView recyclerView;

    @Inject
    PostViewModel postViewModel;

    @Inject
    PostsRecyclerAdapter postsRecyclerAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_view);

        initRecyclerView();
        subscribeObserver();
    }

    private void subscribeObserver() {

        postViewModel.observePosts().removeObservers(getViewLifecycleOwner());

        postViewModel.observePosts().observe(getViewLifecycleOwner(), observer -> {

            if (observer != null) {

                switch (observer.status) {

                    case LOADING: {
                        Log.d(TAG, "onChanged: LOADING...");
                        break;
                    }

                    case SUCCESS: {
                        Log.d(TAG, "onChanged: got posts...");
                        postsRecyclerAdapter.setPosts(observer.data);
                        break;
                    }

                    case ERROR: {
                        Log.e(TAG, "onChanged: ERROR..." + observer.message);
                        break;
                    }
                }
            }
        });
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(postsRecyclerAdapter);
    }
}
