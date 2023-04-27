package com.example.myapplication.ui.projects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MyViewHolder;
import com.example.myapplication.R;
import com.example.myapplication.data.model.Project;

import java.util.List;
public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {
    private List<Project> projects;

    public ProjectAdapter(Context context, List<Project> projects) {
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_item_project, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        holder.nameTextView.setText(projects.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return projects == null ? 0 : projects.size();
    }

    static class ProjectViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;

        ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.projectName);
        }
    }
}
