package com.example.myapplication.ui.projects;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.model.Project;
import com.example.myapplication.data.model.TimeRegistration;
import com.example.myapplication.retrofit.RetrofitClient;
import com.example.myapplication.service.TimeRegistrationService;

import java.util.List;

import retrofit2.Call;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {
    private List<Project> projects;

    public ProjectAdapter(Context context, List<Project> projects) {
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {

        holder.nameTextView.setText(projects.get(holder.getBindingAdapterPosition()).getName());
        // voeg onClick listener toe
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // haal de tijdregistraties op voor het geselecteerde project
                TimeRegistrationService service = RetrofitClient.getRetrofitInstance().create(TimeRegistrationService.class);
                final Project project = projects.get(holder.getBindingAdapterPosition());
                Bundle bundle = new Bundle();
                bundle.putInt("projectId", project.getProjectId());
                Navigation.findNavController(view).navigate(R.id.action_navigation_project_to_detailsProject, bundle);

                Call<List<TimeRegistration>> call = service.getTimeRegistrationsByProjectId(project.getProjectId());
            }});
    }
    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_item_project, parent, false);
        return new ProjectViewHolder(view);
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