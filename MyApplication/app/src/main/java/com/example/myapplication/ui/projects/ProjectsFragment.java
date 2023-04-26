package com.example.myapplication.ui.projects;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
    private TextView nameTextView;
    private ProjectListAdapter projectAdapter;
    List<Project> projects = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_projects, container, false);

        nameTextView = root.findViewById(R.id.listview);
        projectAdapter = new ProjectListAdapter(getContext(), projects);

        // Hier wordt de methode getAllProjects() aangeroepen
        ProjectService service = RetrofitClient.getRetrofitInstance().create(ProjectService.class);
        Call<List<Project>> call = service.getAllProjects();

        call.enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                if (response.isSuccessful()) {
                    projects.addAll(response.body());
                    projectAdapter.notifyDataSetChanged();
                    Log.d("Tag", "Number of projects retrieved: " + projects.size());

                    // Set the name of the first project in the TextView
                    nameTextView.setText(projects.get(0).getName());

                } else {
                    Log.d("Tag", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                Log.d("Tag", "Error: " + t.getMessage());
            }
        });

        return root;
    }
}
