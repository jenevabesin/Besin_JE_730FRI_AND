package com.ucb.menu;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting the Action Bar title and subtitle
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Culinary Adventures");
            getSupportActionBar().setSubtitle("Explore Delicious Recipes and Tips");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId(); // Retrieve the menu item ID

        if (id == R.id.menu_first) {
            // Go to another fragment
            AnotherFragment anotherFragment = new AnotherFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, anotherFragment) // Replace the container with the new fragment
                    .commit();

            // Hide the welcome TextView
            TextView welcomeText = findViewById(R.id.welcome_text);
            welcomeText.setVisibility(View.GONE); // or View.INVISIBLE

            return true;
        } else if (id == R.id.menu_second) {
            // Show DialogFragment
            DialogFragment dialog = new MyDialogFragment();
            dialog.show(getSupportFragmentManager(), "MyDialogFragment");
            return true;
        } else if (id == R.id.menu_third) {
            // Show exit dialog
            showExitDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void navigateToAnotherFragment() {
        Fragment anotherFragment = new AnotherFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, anotherFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit")
                .setMessage("Do you really want to exit?")
                .setPositiveButton("Yes", (dialog, id) -> finish())
                .setNegativeButton("No", (dialog, id) -> dialog.dismiss());
        builder.create().show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Show the welcome TextView when coming back to MainActivity
        TextView welcomeText = findViewById(R.id.welcome_text);
        welcomeText.setVisibility(View.VISIBLE);
    }


}
