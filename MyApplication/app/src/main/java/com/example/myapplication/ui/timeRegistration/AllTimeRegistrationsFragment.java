package com.example.myapplication.ui.timeRegistration;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.DetailsTimeRegist;
import com.example.myapplication.MyAdapter;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentDashboardBinding;
import com.example.myapplication.databinding.FragmentProjectsBinding;
import com.example.myapplication.ui.dashboard.DashboardFragment;
import com.example.myapplication.ui.projects.ProjectsFragment;

import java.util.ArrayList;
import java.util.Arrays;

public class AllTimeRegistrationsFragment extends Fragment {
    private FragmentProjectsBinding binding;
    private RecyclerView recyclerView;

    private MyAdapter myAdapter;
    TextView item;

    String[] data = {"time registration 1", "time registration 2", "time registration 3", "time registration4", "time registration 5", "time registration 6", "time registration 7", "time registration 8", "time registration 9", "time registration 10"};

    public AllTimeRegistrationsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_all_time_registrations, container, false);
        binding = FragmentProjectsBinding.inflate(inflater, container, false);
        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //navigeer naar quick start fragment
                NavHostFragment.findNavController(AllTimeRegistrationsFragment.this)
                        .navigate(R.id.action_navigation_time_to_addProject);

            }
        });
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //navigeer naar quick start fragment
                NavHostFragment.findNavController(AllTimeRegistrationsFragment.this)
                        .navigate(R.id.action_navigation_time_to_detailsTimeRegist);
            }
        });
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_time_registrations, container, false);

        // Initialize RecyclerView and its adapter
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        MyAdapter adapter = new MyAdapter(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;


    }
}
