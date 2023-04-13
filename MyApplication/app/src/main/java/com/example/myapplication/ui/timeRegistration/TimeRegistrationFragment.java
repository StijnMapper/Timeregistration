package com.example.myapplication.ui.timeRegistration;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentTimeRegistrationBinding;

public class TimeRegistrationFragment extends Fragment {
    private FragmentTimeRegistrationBinding binding;
    public TimeRegistrationFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentTimeRegistrationBinding binding = FragmentTimeRegistrationBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Timer");
        }

        // Zoek de buttons op in de view
        ImageView playBtn = view.findViewById(R.id.playBtn);
        ImageView stopBtn = view.findViewById(R.id.stopBtn);
                playBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Start de chronometer
                        binding.chronometer.start();
                        // Verberg de playBtn en toon de stopBtn
                        playBtn.setVisibility(View.GONE);
                        stopBtn.setVisibility(View.VISIBLE);
                    }
                });
                stopBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Stop de chronometer
                        binding.chronometer.stop();
                        // Verberg de stopBtn en toon de playBtn
                        stopBtn.setVisibility(View.GONE);
                        playBtn.setVisibility(View.VISIBLE);
                    }
                });
        return view;
    }


}