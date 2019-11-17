package com.example.dagger.ui.main.post;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dagger.SessionManager;
import com.example.dagger.model.PostModel;
import com.example.dagger.network.auth.Authapi;
import com.example.dagger.ui.main.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class PostViewModel extends ViewModel {

    private static final String TAG = "PostViewModel";


    private final Authapi authapi;
    private final SessionManager sessionManager;
    private int id;

    private MediatorLiveData<Resource<List<PostModel>>> posts;

    @Inject
    public PostViewModel(Authapi authapi, SessionManager sessionManager) {
        this.authapi = authapi;
        this.sessionManager = sessionManager;

        id = sessionManager.getAuthenticatedUser().getValue().data.getId();

        posts = new MediatorLiveData<>();
    }


    public LiveData<Resource<List<PostModel>>> observePosts() {

        posts.setValue(Resource.loading(null));

        final LiveData<Resource<List<PostModel>>> source = LiveDataReactiveStreams.fromPublisher(

                authapi.getUserPosts(id)
                        .onErrorReturn(throwable -> {

                            Log.d(TAG, "apply: ", throwable);

                            PostModel post = new PostModel();
                            post.setId(-1);
                            ArrayList<PostModel> posts = new ArrayList<>();
                            posts.add(post);
                            return posts;
                        })

                        .map((Function<List<PostModel>, Resource<List<PostModel>>>) postModels -> {

                            if(postModels.size()>0){

                                if(postModels.get(0).getId()==-1){
                                    return Resource.error("something went wrong..",null);
                                }
                            }
                            return Resource.success(postModels);
                        })
                .subscribeOn(Schedulers.io())
        );

        posts.addSource(source, listResource -> {

            posts.setValue(listResource);
            posts.removeSource(source);
        });

        return posts;
    }


}
