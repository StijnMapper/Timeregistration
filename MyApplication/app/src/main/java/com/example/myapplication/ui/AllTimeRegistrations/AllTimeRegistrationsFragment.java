package com.example.myapplication.ui.AllTimeRegistrations;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.data.model.TimeRegistration;
import com.example.myapplication.databinding.FragmentAllTimeRegistrationsBinding;
import com.example.myapplication.retrofit.RetrofitClient;
import com.example.myapplication.service.TimeRegistrationService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllTimeRegistrationsFragment extends Fragment {
    private FragmentAllTimeRegistrationsBinding binding;
    private RecyclerView recyclerView;
    List<TimeRegistration> registrations;

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
        binding = FragmentAllTimeRegistrationsBinding.inflate(inflater, container, false);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_time_registrations, container, false);

        // Initialize RecyclerView and its adapter
        RecyclerView recyclerView = binding.recyclerview;
        NavController navController = NavHostFragment.findNavController(this);

        MyAdapter adapter = new MyAdapter(registrations, navController);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       TimeRegistrationService service = RetrofitClient.getRetrofitInstance().create(TimeRegistrationService.class);
        //Retrieve all projects
        Call<List<TimeRegistration>> call = service.getTimeRegistrations();

        //asynchronous call to fetch all the projects and add the list of projects to the adapter.
        //this done by using enqueue method of Call, which executes the call in a separate thread and
        // processes the response in the callbacks that are defined.
        call.enqueue(new Callback<List<TimeRegistration>>() {
            @Override
            public void onResponse(Call<List<TimeRegistration>> call, Response<List<TimeRegistration>> response) {
                //success? , the onResponse callback retrieves the list of projects from the response
                // adds it to the adapter using the setProjects method.
                if (response.isSuccessful()) {
                    registrations = response.body();
                    myAdapter.setRegistrations(registrations);
                    Log.d("Tag", "Number of all registrations retrieved: " + registrations.size());
                }
            }

            @Override
            public void onFailure(Call<List<TimeRegistration>> call, Throwable t) {
                Log.d("Tag", "Error: no list of registrations" + t.getMessage());

            }
        });

        return binding.getRoot();


    }
}
