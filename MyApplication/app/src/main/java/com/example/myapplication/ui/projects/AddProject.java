package com.example.myapplication.ui.projects;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.myapplication.R;
import com.example.myapplication.data.model.Project;
import com.example.myapplication.databinding.FragmentAddProjectBinding;
import com.example.myapplication.retrofit.RetrofitClient;
import com.example.myapplication.service.ProjectService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProject extends Fragment {
    public AddProject() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void createProject(Project project) {
        ProjectService service = RetrofitClient.getRetrofitInstance().create(ProjectService.class);
        Call<Project> call = service.createProject(project);
        call.enqueue(new Callback<Project>() {
            @Override
            public void onResponse(Call<Project> call, Response<Project> response) {
                // project is succesvol toegevoegd
                Log.d("Tag","project toegevoegd");
                // navigeer terug naar het ProjectsFragment
                // NavHostFragment.findNavController(AddProject.this)
                // .navigate(R.id.action_addProject_to_navigation_project);
            }

            @Override
            public void onFailure(Call<Project> call, Throwable t) {
                // er is een fout opgetreden
                Toast.makeText(getContext(), "Er is een fout opgetreden: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentAddProjectBinding binding = FragmentAddProjectBinding.inflate(inflater, container, false);
        Project project = new Project();
        project.setName(binding.name.getText().toString());
        project.setDescription(binding.description.getText().toString());
        project.setUser("null");
        project.setStatus("null");
        project.setBillable(false);
        project.setClient("null");
        project.setFinalDeadline("null");

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Add project");
        }
        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createProject(project);
            }
        });
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_project, container, false);
    }

}