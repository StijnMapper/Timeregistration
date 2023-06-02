package com.example.myapplication.ui.projects;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.AddProject;
import com.example.myapplication.R;
import com.example.myapplication.data.model.Project;
import com.example.myapplication.data.model.TimeRegistration;
import com.example.myapplication.retrofit.RetrofitClient;
import com.example.myapplication.service.ProjectService;
import com.example.myapplication.service.TimeRegistrationService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {
    private List<Project> projects;
    private Context context;

    public ProjectAdapter(Context context, List<Project> projects) {
        this.context = context;
        this.projects = projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        Project project = projects.get(holder.getBindingAdapterPosition());

        holder.nameTextView.setText(project.getName());
       // Log.d("getname",projects.get(holder.getBindingAdapterPosition()).getName());
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
    private void createProject(Project project) {
        ProjectService service = RetrofitClient.getRetrofitInstance().create(ProjectService.class);
        Call<Project> call = service.createProject(project);
        call.enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                if (response.isSuccessful()) {
                    Project createdProject = response.body();
                    projects.add(createdProject);
                    Log.d(com.example.myapplication.AddProject.class.getSimpleName(), "Project succesvol toegevoegd");
                    System.out.println("Project succesvol toegevoegd");


                } else {
                    Log.e(com.example.myapplication.AddProject.class.getSimpleName(), "Fout bij toevoegen van project: " + response.code());
                    System.out.println("Fout bij toevoegen van project: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                Log.e(AddProject.class.getSimpleName(), t.getMessage(), t);
                System.out.println("error: " + t.getMessage());
            }
        });
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
        ImageButton add;

        ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.projectName);
            add = itemView.findViewById(R.id.button_add);
        }
    }
}