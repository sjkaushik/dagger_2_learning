package com.example.dagger.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.dagger.BaseActivity;
import com.example.dagger.R;
import com.example.dagger.model.UserModel;
import com.google.android.material.navigation.NavigationView;

import javax.inject.Inject;
import javax.inject.Named;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    @Named("user_single")
    UserModel user;


    @Inject
    @Named("user_auth")
    UserModel userAuth;

    private static final String TAG = "MainActivity";
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        init();

        Log.d(TAG, "onCreate: UserModel " + user);
        Log.d(TAG, "onCreate: UserModel Auth" + userAuth);

    }

    private void init() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
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

            case android.R.id.home: {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.nav_profile: {

                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.drawer_layout, true)
                        .build();

                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(
                        R.id.profileFragment,
                        null,
                        navOptions
                );

                break;
            }

            case R.id.nav_posts: {

                if (isValidDestination(R.id.postFragment)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.postFragment);
                }

                break;
            }
        }

        menuItem.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private boolean isValidDestination(int destination) {
        return destination != Navigation.findNavController(this, R.id.nav_host_fragment).getCurrentDestination().getId();
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), drawerLayout);
    }
}
