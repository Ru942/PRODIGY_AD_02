package com.example.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<Task> tasks;
    private OnTaskClickedListener onTaskClickedListener;
    private OnDeleteClickedListener onDeleteClickedListener;

    public TaskAdapter(List<Task> tasks, OnTaskClickedListener onTaskClickedListener, OnDeleteClickedListener onDeleteClickedListener) {
        this.tasks = tasks;
        this.onTaskClickedListener = onTaskClickedListener;
        this.onDeleteClickedListener = onDeleteClickedListener;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the item view layout for each task item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        // Get the task at the given position
        Task task = tasks.get(position);
        holder.title.setText(task.getTitle());
        holder.completed.setChecked(task.isCompleted());

        // Set listener for checkbox click to update task completion status
        holder.completed.setOnCheckedChangeListener((buttonView, isChecked) -> onTaskClickedListener.onTaskClicked(task));

        // Set listener for delete button click
        holder.deleteButton.setOnClickListener(v -> onDeleteClickedListener.onDeleteClicked(task));
    }

    @Override
    public int getItemCount() {
        return tasks.size();  // Return the total number of tasks
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        CheckBox completed;
        TextView deleteButton;

        public TaskViewHolder(View itemView) {
            super(itemView);
            // Bind the views from the item layout to the ViewHolder
            title = itemView.findViewById(R.id.taskTitle);
            completed = itemView.findViewById(R.id.taskCheckBox);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    // Interface for task clicked listener
    public interface OnTaskClickedListener {
        void onTaskClicked(Task task);
    }

    // Interface for delete clicked listener
    public interface OnDeleteClickedListener {
        void onDeleteClicked(Task task);
    }
}
