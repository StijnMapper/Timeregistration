package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.myapplication.data.model.Project;
import com.example.myapplication.databinding.FragmentAddProjectBinding;
import com.example.myapplication.retrofit.RetrofitClient;
import com.example.myapplication.service.ProjectService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProject extends Fragment {
    private EditText projectNameEditText;
    private EditText goalEditText;
    private EditText descriptionEditText;
    private EditText clientEditText;
    private EditText deadlineEditText;
    private EditText budgetEditText;
    private EditText billableEditText;
    private Button saveButton;
    private List<Project> projectList=new ArrayList<>();

    public AddProject() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentAddProjectBinding binding = FragmentAddProjectBinding.inflate(inflater, container, false);

        // Vind alle views in de lay-out
        projectNameEditText = binding.name;
        goalEditText = binding.goal;
        descriptionEditText = binding.description;
        clientEditText = binding.client;
        deadlineEditText = binding.deadline;
        budgetEditText = binding.budget;
        billableEditText = binding.billable;
        saveButton = binding.save;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Haal de waarden op uit de tekstvelden
                String projectName = projectNameEditText.getText().toString();
                String goal = goalEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                String client = clientEditText.getText().toString();
                String deadline = deadlineEditText.getText().toString();
                double budget = Double.parseDouble(budgetEditText.getText().toString());
                boolean billable = Boolean.parseBoolean(billableEditText.getText().toString());

                // Maak een nieuw Project-object aan met de opgehaalde waarden
                Project project = new Project();
                project.setName(projectName);
                project.setGoal(goal);
                project.setDescription(description);
                project.setClient(client);
                project.setFinalDeadline(deadline);
                project.setBudget(budget);
                project.setBillable(billable);

                // Roep de methode aan om het project naar de backend te verzenden
                createProject(project);
            }
        });

        return binding.getRoot();
    }

    private void createProject(Project project) {
        ProjectService service = RetrofitClient.getRetrofitInstance().create(ProjectService.class);
        Call<Project> call = service.createProject(project);
        call.enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                if (response.isSuccessful()) {
                    Project createdProject = response.body();
                    projectList.add(createdProject);
                    Log.d(AddProject.class.getSimpleName(), "Project succesvol toegevoegd");
                    System.out.println("Project succesvol toegevoegd");


            } else {
                    Log.e(AddProject.class.getSimpleName(), "Fout bij toevoegen van project: " + response.code());
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
}