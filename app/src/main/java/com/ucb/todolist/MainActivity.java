package com.ucb.todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import Adapter.TaskListAdapter;
import ModelClass.TaskItem;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private ArrayList<TaskItem> taskList;
    private TaskListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        editText = findViewById(R.id.editText);
        Button addButton = findViewById(R.id.addButton);

        taskList = new ArrayList<>();
        adapter = new TaskListAdapter(this, taskList);
        listView.setAdapter(adapter);

        // Add button click listener
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskText = editText.getText().toString().trim();
                if (!taskText.isEmpty()) {
                    taskList.add(new TaskItem(taskText, false, R.drawable.ic_launcher_foreground));
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Enter a task", Toast.LENGTH_SHORT).show();
                }
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the task that was long-pressed
                TaskItem task = taskList.get(position);
                // Show the edit/delete dialog
                showEditDeleteDialog(task, position);
                return true; // Return true to indicate that the long press was handled
            }
        });

    }
    private void showEditDeleteDialog(TaskItem task, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit or Delete");
        builder.setMessage("Would you like to edit or delete this task?");
        builder.setPositiveButton("Edit", (dialog, which) -> {
            editText.setText(task.getTaskName());
            taskList.remove(position);
            adapter.notifyDataSetChanged();
        });
        builder.setNegativeButton("Delete", (dialog, which) -> {
            taskList.remove(position);
            adapter.notifyDataSetChanged();
        });
        builder.show();
    }
}
