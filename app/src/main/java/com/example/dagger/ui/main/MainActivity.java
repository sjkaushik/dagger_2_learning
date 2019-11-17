package com.example.dagger.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.Nullable;

import com.example.dagger.BaseActivity;
import com.example.dagger.R;
import com.example.dagger.ui.main.post.PostFragment;
import com.example.dagger.ui.main.profile.ProfileFragment;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toast.makeText(this, "Main Activity", Toast.LENGTH_SHORT).show();

        attachView();
    }

    private void attachView() {
        getSupportFragmentManager().beginTransaction().replace(R.id.main_profile, new PostFragment()).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.logout:

                sessionManager.logout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
