package com.example.dagger.ui.main.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dagger.R;
import com.example.dagger.model.UserModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {

    private static final String TAG = "ProfileFragment";

    private TextView email, name, website;
    @Inject
    ProfileViewModel profileViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);
        website = view.findViewById(R.id.website);

        subscribeObserver();

    }

    private void subscribeObserver() {

        profileViewModel.getUserData().removeObservers(getViewLifecycleOwner());
        profileViewModel.getUserData().observe(getViewLifecycleOwner(), userModelAuthResource -> {

            if (userModelAuthResource != null) {

                switch (userModelAuthResource.status) {

                    case AUTHENTICATED:

                        setUserDetails(userModelAuthResource.data);
                        break;

                    case ERROR:

                        setErrorDetails(userModelAuthResource.message);
                        break;
                }
            }
        });

    }

    private void setErrorDetails(String message) {

        name.setText(message);
        email.setText(getString(R.string.error));
        website.setText(getString(R.string.error));
    }

    private void setUserDetails(UserModel data) {

        name.setText(data.getUsername());
        email.setText(data.getEmail());
        website.setText(data.getWebsite());
    }


}
