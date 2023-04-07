package com.example.myapplication.ui.start.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentLoginBinding binding = FragmentLoginBinding.inflate(inflater, container, false);

        // Voeg een klik-luisteraar toe aan de login-knop
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Navigeer naar een bestemming in de andere NavGraph
                // currentNavController.navigate(R.id.action_registerFragment_to_mobile_navigation/R.id.mobile_navigation);
                // Navigeer naar het LoginFragment
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.action_loginFragment_to_mainActivity2);
                // Voer hier de acties uit die moeten worden uitgevoerd wanneer er op de login-knop wordt geklikt
            }
        });
        binding.forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Navigeer naar een bestemming in de andere NavGraph
                // currentNavController.navigate(R.id.action_registerFragment_to_mobile_navigation/R.id.mobile_navigation);
                // Navigeer naar het LoginFragment
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.action_loginFragment_to_resetPasswordFragment);
                // Voer hier de acties uit die moeten worden uitgevoerd wanneer er op de login-knop wordt geklikt
            }
        });

        // Geef de gerenderde view terug
        return binding.getRoot();
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_login, container, false);
        /*
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigeer naar het LoginFragment
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.action_loginFragment_to_dashboardFragment);
            }
        });
        //Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_mobile_navigation);
        return binding.getRoot();
        */

    }
}