package com.sharifdev.torobche;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        BottomNavigationView navigationBar = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigationBar.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        navigationBar.getMenu().getItem(1).setChecked(true);

        // load home fragment by default
        loadFragment(new HomeFragment());

    }


    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
    * listener for navigation bottom menu
     **/
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.menu_activity:
                    loadFragment(new ActivityFragment());
                    return true;
                case R.id.menu_home:
                    loadFragment(new HomeFragment());
                    return true;
                case R.id.menu_game:
                    loadFragment(new GameFragment());
                    return true;
                case R.id.menu_chat:
                    loadFragment(new ChatFragment());
                    return true;
            }
            return false;
        }
    };



}