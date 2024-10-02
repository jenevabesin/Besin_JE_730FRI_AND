package com.ucb.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class NewsContentFragment extends Fragment {

    public static NewsContentFragment newInstance(String headline, String content) {
        NewsContentFragment fragment = new NewsContentFragment();
        Bundle args = new Bundle();
        args.putString("headline", headline);
        args.putString("content", content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_content, container, false);

        // Retrieve headline and content from arguments
        Bundle args = getArguments();
        if (args != null) {
            String headline = args.getString("headline");
            String content = args.getString("content");

            // Find TextViews and set the text
            TextView headlineTextView = view.findViewById(R.id.news_headline);
            TextView contentTextView = view.findViewById(R.id.news_content);

            headlineTextView.setText(headline);
            contentTextView.setText(content);
        }

        return view;
    }
}
