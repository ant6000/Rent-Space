package com.example.too_let_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    NewsFeedFragment newsFeedFragment = new NewsFeedFragment();
    SearchFragment searchFragment = new SearchFragment();
    UploadFragment uploadFragment = new UploadFragment();
    SettingsFragment settingsFragment = new SettingsFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getSupportActionBar().setTitle("Search using Location");

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.wrapper_,newsFeedFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home_:
                        getSupportFragmentManager().beginTransaction().replace(R.id.wrapper_,newsFeedFragment).commit();
                        return true;
//                    case R.id.search_:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.wrapper_,searchFragment).commit();
//                        return true;
                    case R.id.upload_:
                        getSupportFragmentManager().beginTransaction().replace(R.id.wrapper_,uploadFragment).commit();
                        return true;
                    case R.id.settings:
                        getSupportFragmentManager().beginTransaction().replace(R.id.wrapper_,settingsFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}