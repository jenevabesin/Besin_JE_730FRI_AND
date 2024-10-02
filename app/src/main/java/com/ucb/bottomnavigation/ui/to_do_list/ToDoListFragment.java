package com.ucb.bottomnavigation.ui.to_do_list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ucb.bottomnavigation.R;

import java.util.ArrayList;

public class ToDoListFragment extends Fragment {

    private ListView listView;
    private EditText editText;
    private Button addButton;
    private TaskListAdapter adapter;
    private ArrayList<TaskItem> taskList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_to_do_list, container, false);

        // Initialize views
        listView = view.findViewById(R.id.listView);
        editText = view.findViewById(R.id.editText);
        addButton = view.findViewById(R.id.addButton);

        // Initialize task list and adapter
        taskList = new ArrayList<>();
        adapter = new TaskListAdapter(getContext(), taskList);
        listView.setAdapter(adapter);

        addButton.setOnClickListener(v -> {
            String taskName = editText.getText().toString();
            if (!taskName.isEmpty()) {
                // Create a new task item
                TaskItem newTask = new TaskItem(taskName, false, R.drawable.ic_launcher_foreground);
                taskList.add(newTask);
                adapter.notifyDataSetChanged(); // Notify adapter of the change
                editText.setText(""); // Clear the input field
            }
        });

        return view;
    }
}