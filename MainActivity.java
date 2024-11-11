package com.example.todoapp;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends ComponentActivity {

    private RecyclerView taskRecyclerView;
    private EditText taskEditText;
    private DatabaseHelper databaseHelper;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskRecyclerView = findViewById(R.id.taskRecyclerView);
        taskEditText = findViewById(R.id.taskEditText);
        databaseHelper = new DatabaseHelper(this);

        // Setup RecyclerView
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadTasks();

        // Add task button logic
        findViewById(R.id.addTaskButton).setOnClickListener(v -> {
            String taskTitle = taskEditText.getText().toString();
            if (!taskTitle.isEmpty()) {
                Task task = new Task(taskTitle);
                databaseHelper.addTask(task);
                loadTasks();
                taskEditText.setText("");
            } else {
                Toast.makeText(MainActivity.this, "Please enter a task", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadTasks() {
        List<Task> tasks = databaseHelper.getAllTasks();
        taskAdapter = new TaskAdapter(tasks, this::onTaskClicked, this::onDeleteClicked);
        taskRecyclerView.setAdapter(taskAdapter);
    }

    private void onTaskClicked(Task task) {
        task.setCompleted(!task.isCompleted());
        databaseHelper.updateTask(task);
        loadTasks();
    }

    private void onDeleteClicked(Task task) {
        databaseHelper.deleteTask(task.getId());
        loadTasks();
    }
}
