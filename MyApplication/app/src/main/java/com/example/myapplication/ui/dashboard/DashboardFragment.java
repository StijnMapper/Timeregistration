package com.example.myapplication.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentDashboardBinding binding = FragmentDashboardBinding.inflate(inflater, container, false);

        binding.quickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //navigeer naar quick start fragment
                NavHostFragment.findNavController(DashboardFragment.this)
                        .navigate(R.id.action_navigation_dashboard_to_quickStartFragment);

            }
        });

        binding.withoutTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //navigeer naar Without timer fragment
                NavHostFragment.findNavController(DashboardFragment.this)
                        .navigate(R.id.action_navigation_dashboard_to_withoutTimerFragment);

            }
        });
        binding.startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //navigeer naar time registration fragment
                NavHostFragment.findNavController(DashboardFragment.this)
                        .navigate(R.id.action_navigation_dashboard_to_timeRegistrationFragment);

            }
        });

        return binding.getRoot();

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}