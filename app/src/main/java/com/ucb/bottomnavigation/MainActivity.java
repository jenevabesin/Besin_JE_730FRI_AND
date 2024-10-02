package com.ucb.bottomnavigation;

import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.ucb.bottomnavigation.ui.calculator.CalculatorFragment;
import com.ucb.bottomnavigation.ui.profile.ProfileFragment;
import com.ucb.bottomnavigation.ui.to_do_list.ToDoListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        // Load the default fragment (ProfileFragment)
        loadFragment(new ProfileFragment());

        // Set the default selected item in the BottomNavigationView
        bottomNav.setSelectedItemId(R.id.nav_profile);

        // Set up the bottom navigation
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                if (item.getItemId() == R.id.nav_calculator) {
                    selectedFragment = new CalculatorFragment();
                } else if (item.getItemId() == R.id.nav_todo) {
                    selectedFragment = new ToDoListFragment();
                } else if (item.getItemId() == R.id.nav_profile) {
                    selectedFragment = new ProfileFragment();
                }

                if (selectedFragment != null) {
                    loadFragment(selectedFragment);
                }

                return true;
            }
        });
    }

    // Function to load the selected fragment
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}