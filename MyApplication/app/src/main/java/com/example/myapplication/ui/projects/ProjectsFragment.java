package com.example.myapplication.ui.projects;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentDashboardBinding;
import com.example.myapplication.databinding.FragmentProjectsBinding;
import com.example.myapplication.ui.dashboard.DashboardFragment;
import com.example.myapplication.ui.timeRegistration.AllTimeRegistrationsFragment;


public class ProjectsFragment extends Fragment {
    ListView list;
    ArrayAdapter<String>adapter;
    String[] data = {"project 1","project 2", "project 3", "project 4", "project 5", "project 6", "project 7", "project 8", "project 9", "project 10" };
public ProjectsFragment() {}
private FragmentProjectsBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProjectsBinding.inflate(inflater, container, false);
        binding = FragmentProjectsBinding.inflate(inflater, container, false);


        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //navigeer naar quick start fragment
                NavHostFragment.findNavController(ProjectsFragment.this)
                        .navigate(R.id.action_navigation_project_to_addProject);

            }
        });
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //navigeer naar quick start fragment
                NavHostFragment.findNavController(ProjectsFragment.this)
                        .navigate(R.id.action_navigation_project_to_detailsProject);
            }
        });
        View root = binding.getRoot();
        View view = inflater.inflate(R.layout.fragment_projects,container,false);
        list=(ListView) root.findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,data);
        list.setAdapter(adapter);
        return root;
    }



}