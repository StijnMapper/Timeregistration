package com.example.myapplication.ui.AllTimeRegistrations;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.data.model.TimeRegistration;
import com.example.myapplication.databinding.FragmentAllTimeRegistrationsBinding;
import com.example.myapplication.retrofit.RetrofitClient;
import com.example.myapplication.service.TimeRegistrationService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllTimeRegistrationsFragment extends Fragment {

    private RecyclerView recyclerView;
    List<TimeRegistration> registrations =  new ArrayList<>();

    private MyAdapter myAdapter;

    public AllTimeRegistrationsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default ) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
        */

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentAllTimeRegistrationsBinding binding = FragmentAllTimeRegistrationsBinding.inflate(inflater, container, false);

        // Initialize RecyclerView and its adapter
        NavController navController = NavHostFragment.findNavController(this);

        myAdapter = new MyAdapter(getContext(), registrations);
        recyclerView = binding.recyclerview;
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        fetchData();
        return binding.getRoot();


    }

    private void fetchData() {
        TimeRegistrationService service = RetrofitClient.getRetrofitInstance().create(TimeRegistrationService.class);
        Call<List<TimeRegistration>> call = service.getAllTimeRegistrations();

        call.enqueue(new Callback<List<TimeRegistration>>() {
            @Override
            public void onResponse(Call<List<TimeRegistration>> call, Response<List<TimeRegistration>> response) {
                if (response.isSuccessful()) {
                    registrations = response.body();
                    myAdapter.setRegistrations(registrations);
                    Log.d(AllTimeRegistrationsFragment.class.getSimpleName(), "Number of all registrations retrieved: " + registrations.size());
                }
            }

            @Override
            public void onFailure(Call<List<TimeRegistration>> call, Throwable t) {
                Log.d(AllTimeRegistrationsFragment.class.getSimpleName(), "Error: " + t.getMessage());
            }
        });
    }

}
