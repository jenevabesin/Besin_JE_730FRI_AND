package com.ucb.news;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements HeadlineListFragment.OnHeadlineSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if the savedInstanceState is null to avoid re-adding fragments
        if (savedInstanceState == null) {
            HeadlineListFragment listFragment = new HeadlineListFragment();
            NewsContentFragment detailFragment = NewsContentFragment.newInstance("", "");

            // Check the orientation
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                // Add the headline list fragment in portrait mode
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.headline_fragment, listFragment)
                        .commit();

                // Initially hide the content fragment
                findViewById(R.id.content_fragment).setVisibility(View.GONE);
            } else {
                // Add both fragments in landscape mode
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.headline_fragment, listFragment)
                        .replace(R.id.content_fragment, detailFragment)
                        .commit();
            }
        }
    }

    @Override
    public void onHeadlineSelected(String headline, String content) {
        NewsContentFragment newsContentFragment = NewsContentFragment.newInstance(headline, content);

        boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        // Replace the content fragment
        replaceFragment(newsContentFragment, isPortrait);

        // If in portrait mode, hide the headline list
        if (isPortrait) {
            findViewById(R.id.headline_fragment).setVisibility(View.GONE); // Hide the headline list
            findViewById(R.id.content_fragment).setVisibility(View.VISIBLE); // Show content fragment
        }
    }

    // Method to handle fragment replacement, addToBackStack only in portrait
    private void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_fragment, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    // Optional: Handle configuration changes dynamically
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        adjustFragmentVisibility();
    }

    // Method to adjust fragment visibility based on orientation
    private void adjustFragmentVisibility() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Fragment headlineFragment = getSupportFragmentManager().findFragmentById(R.id.headline_fragment);
        Fragment contentFragment = getSupportFragmentManager().findFragmentById(R.id.content_fragment);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (headlineFragment != null) {
                transaction.hide(headlineFragment);
            }
        } else {
            if (headlineFragment != null) {
                transaction.show(headlineFragment);
            }
            if (contentFragment != null) {
                transaction.show(contentFragment);
            }
        }
        transaction.commit();
    }

    // Override the onBackPressed method
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // Check the fragment stack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            // If there are entries in the back stack, pop the fragment
            getSupportFragmentManager().popBackStack();
        } else {
            // If no fragments are left, exit the activity
            finish();
        }

        //  restore the visibility of the headline list in portrait mode
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            findViewById(R.id.headline_fragment).setVisibility(View.VISIBLE);
            findViewById(R.id.content_fragment).setVisibility(View.GONE);
        }
    }
}
