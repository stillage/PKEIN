package com.example.pkein;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        BottomNavigationView bottonNav = findViewById(R.id.botton_navigation);
        bottonNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragman_container,
                new Homefragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    switch (menuItem.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new Homefragment();
                            break;
                        case R.id.nav_info:
                            selectedFragment = new Infofragment();
                            break;
                        case R.id.nav_help:
                            selectedFragment = new Helpfragment();
                            break;
                    }

                    int fragman_container = R.id.fragman_container;
                    getSupportFragmentManager().beginTransaction().replace(
                            fragman_container, selectedFragment).commit();
                    return true;
                }
            };
}