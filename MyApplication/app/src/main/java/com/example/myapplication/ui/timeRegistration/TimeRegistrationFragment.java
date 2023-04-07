package com.example.myapplication.ui.timeRegistration;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentChronometerBinding;
import com.example.myapplication.databinding.FragmentLoginBinding;
import com.example.myapplication.databinding.FragmentTimeRegistrationBinding;
import com.example.myapplication.ui.start.login.LoginFragment;

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
        binding.playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.chronometer.start();
            }
        });

        // Return the root view of the binding
        return binding.getRoot();
    }


}