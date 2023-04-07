package com.example.myapplication.ui.timeRegistration;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentProjectsBinding;

public class AllTimeRegistrationsFragment extends Fragment {
    private FragmentProjectsBinding binding;
    ArrayAdapter<String> adapter;
    ListView list;
    String[] data = {"time registration 1","time registration 2", "time registration 3", "time registration4", "time registration 5", "time registration 6", "time registration 7", "time registration 8", "time registration 9", "time registration 10" };
    public AllTimeRegistrationsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_all_time_registrations, container, false);

            binding = FragmentProjectsBinding.inflate(inflater, container, false);
            View root = binding.getRoot();
            View view = inflater.inflate(R.layout.fragment_all_time_registrations,container,false);
            list=(ListView) root.findViewById(R.id.listview);
            adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,data);
            list.setAdapter(adapter);
            return root;
    }
}