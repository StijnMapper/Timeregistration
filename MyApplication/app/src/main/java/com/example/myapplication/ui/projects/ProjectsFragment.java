package com.example.myapplication.ui.projects;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentProjectsBinding;


public class ProjectsFragment extends Fragment {
    ListView list;
    ArrayAdapter<String>adapter;
    String[] data = {"project 1","project 2", "project 3", "project 4", "project 5", "project 6", "project 7", "project 8", "project 9", "project 10" };
public ProjectsFragment() {}
private FragmentProjectsBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProjectsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        View view = inflater.inflate(R.layout.fragment_projects,container,false);
        list=(ListView) root.findViewById(R.id.listview);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,data);
        list.setAdapter(adapter);
        return root;
    }



}