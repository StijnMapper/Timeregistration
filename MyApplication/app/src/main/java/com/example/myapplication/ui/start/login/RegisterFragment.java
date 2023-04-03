package com.example.myapplication.ui.start.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;

    public RegisterFragment() {
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
       // return inflater.inflate(R.layout.fragment_register, container, false);
        FragmentRegisterBinding binding = FragmentRegisterBinding.inflate(inflater, container, false);

        // Voeg een klik-luisteraar toe aan de login-knop
        binding.signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Navigeer naar het LoginFragment
                NavHostFragment.findNavController(RegisterFragment.this)
                        .navigate(R.id.action_registerFragment_to_loginFragment);
                // Voer hier de acties uit die moeten worden uitgevoerd wanneer er op de login-knop wordt geklikt
            }
        });

        // Geef de gerenderde view terug
        return binding.getRoot();

    }
}