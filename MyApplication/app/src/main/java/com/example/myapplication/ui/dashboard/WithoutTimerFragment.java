package com.example.myapplication.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.myapplication.databinding.FragmentWithoutTimerBinding;

public class WithoutTimerFragment extends Fragment {
    public WithoutTimerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    FragmentWithoutTimerBinding binding = FragmentWithoutTimerBinding.inflate(inflater, container,false);
    EditText startTimeText = binding.startTime;
    EditText endTimeText = binding.endTime;
    Bundle bundle = getArguments();
    if (bundle != null) {
        String startTime = bundle.getString("startTime");
        String endTime = bundle.getString("endTime");
        startTimeText.setText(startTime);
        endTimeText.setText(endTime);
    }

    return binding.getRoot();
}

}