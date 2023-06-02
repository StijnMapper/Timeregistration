package com.example.myapplication.ui.projects;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.model.Project;
import com.example.myapplication.databinding.FragmentProjectsBinding;
import com.example.myapplication.retrofit.RetrofitClient;
import com.example.myapplication.service.ProjectService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectsFragment extends Fragment {
    private ProjectAdapter projectAdapter;
    private RecyclerView recyclerView;
    private int projectId;
    private List<Project> projects = new ArrayList<>();
    public ProjectsFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the selected project ID from the arguments
        if (getArguments() != null) {
            projectId = getArguments().getInt("projectId");
        }}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_projects, container, false);
        FragmentProjectsBinding binding = FragmentProjectsBinding.inflate(inflater, container, false);
        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ProjectsFragment.this)
                        .navigate(R.id.action_navigation_project_to_addProject);
            }
        });

        //Initialize adapter, contains a list of project objects as dataset
        projectAdapter = new ProjectAdapter(getContext(), projects);
        //show project objects in RecyclerView.
        recyclerView = binding.listview;
        recyclerView.setAdapter(projectAdapter);
        //set the view of the recyclerview, show it vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fetchprojects();
        return binding.getRoot();
    }
    private void fetchprojects(){
        //service for access to getAllProjects
        ProjectService service = RetrofitClient.getRetrofitInstance().create(ProjectService.class);
        //Retrieve all projects
        Call<List<Project>> call = service.getAllProjects();

        //asynchronous call to fetch all the projects and add the list of projects to the adapter.
        //this done by using enqueue method of Call, which executes the call in a separate thread and
        // processes the response in the callbacks that are defined.
        call.enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                //success? , the onResponse callback retrieves the list of projects from the response
                // adds it to the adapter using the setProjects method.
                if (response.isSuccessful()) {
                    projects = response.body();
                    projectAdapter.setProjects(projects);
                      Log.d("Tag", "Number of projects retrieved: " + projects.size());
                }
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                Log.d("Tag", "Error: " + t.getMessage());

            }
        });
    }
}