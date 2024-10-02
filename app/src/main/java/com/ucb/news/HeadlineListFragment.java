package com.ucb.news;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;
import android.util.Log;

public class HeadlineListFragment extends ListFragment {

    public interface OnHeadlineSelectedListener {
        void onHeadlineSelected(String headline, String content);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String[] headlines = {
                getString(R.string.headline1),
                getString(R.string.headline2),
                getString(R.string.headline3),
                getString(R.string.headline4)
        };

        // ArrayAdapter to display the headlines in the ListView
        setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, headlines));
    }

    @Override
    public void onListItemClick(ListView l, @NonNull View v, int position, long id) {
        // Get the content corresponding to the selected headline from strings
        String[] contents = {
                getString(R.string.content_1),
                getString(R.string.content_2),
                getString(R.string.content_3),
                getString(R.string.content_4)
        };

        String content = contents[position];
        Log.d("HeadlineListFragment", "Selected headline: " + (String) l.getItemAtPosition(position) + ", content: " + content);

        OnHeadlineSelectedListener activity = (OnHeadlineSelectedListener) getActivity();
        if (activity != null) {
            activity.onHeadlineSelected((String) l.getItemAtPosition(position), content);
        }
    }
}
