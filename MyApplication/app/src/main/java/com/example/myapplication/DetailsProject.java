package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.data.model.TimeRegistration;
import com.example.myapplication.databinding.FragmentDetailsProjectBinding;
import com.example.myapplication.retrofit.RetrofitClient;
import com.example.myapplication.service.TimeRegistrationService;
import com.example.myapplication.ui.timeRegistration.RegistrationsOfProjectAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsProject extends Fragment {

    private int projectId;
    private RecyclerView recyclerView;
    private RegistrationsOfProjectAdapter adapter;
    private List<TimeRegistration> timeRegistrations = new ArrayList<>();

    public DetailsProject() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the selected project ID from the arguments
        if (getArguments() != null) {
            projectId = getArguments().getInt("projectId");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Details Project");
        }
        FragmentDetailsProjectBinding binding = FragmentDetailsProjectBinding.inflate(inflater, container, false);

        adapter = new RegistrationsOfProjectAdapter(getContext(), timeRegistrations);
        recyclerView = binding.recyclerview;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        fetchTimeRegistrations();
        return binding.getRoot();
    }


    private void fetchTimeRegistrations() {
        TimeRegistrationService service = RetrofitClient.getRetrofitInstance().create(TimeRegistrationService.class);
        Call<List<TimeRegistration>> call = service.getTimeRegistrationsByProjectId(projectId);
        call.enqueue(new Callback<List<TimeRegistration>>() {
            @Override
            public void onResponse(Call<List<TimeRegistration>> call, Response<List<TimeRegistration>> response) {

                if (response.isSuccessful()) {
                    timeRegistrations = response.body();
                    adapter.setRegistrations(timeRegistrations);
                    Log.d(DetailsProject.class.getSimpleName(), "Number of registraions retrieved: " + timeRegistrations.size());
                }
            }

            @Override
            public void onFailure(Call<List<TimeRegistration>> call, Throwable t) {
                Log.e(DetailsProject.class.getSimpleName(), t.getMessage(), t);
            }
        });
    }
}

