package com.example.myapplication.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentQuickStartBinding;


public class QuickStartFragment extends Fragment {

        public QuickStartFragment() {
            // Required empty public constructor
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            FragmentQuickStartBinding binding = FragmentQuickStartBinding.inflate(inflater, container, false);
            View view = binding.getRoot();
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle("Quick Start");
            }
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
            // Inflate the layout for this fragment
            return view;
        }
    }