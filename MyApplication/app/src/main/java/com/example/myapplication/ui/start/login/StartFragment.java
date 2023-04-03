package com.example.myapplication.ui.start.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentStartBinding;

public class StartFragment extends Fragment {
    private FragmentStartBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentStartBinding.inflate(getLayoutInflater());
        //final View view = inflater.inflate(R.layout.fragment_login, container,false);
        //final Activity activity = getActivity();
        /*
        binding.fragLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Maak een nieuwe instantie van het LoginFragment
                LoginFragment loginFragment = new LoginFragment();

                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

                transaction.replace(R.id.action_startFragment_to_loginFragment, loginFragment);

                transaction.addToBackStack(null);

                transaction.commit();
            }
        });*/
        binding.fragLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigeer naar het LoginFragment
                NavHostFragment.findNavController(StartFragment.this)
                        .navigate(R.id.action_startFragment_to_loginFragment);
            }
        });
        binding.fragRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigeer naar het LoginFragment
                NavHostFragment.findNavController(StartFragment.this)
                        .navigate(R.id.action_startFragment_to_registerFragment);
            }
        });

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Bundle bundle = new Bundle();
        // bundle.putLong("fragment_start", null);
       // NavHostFragment.findNavController(this).navigate(R.id.action_startFragment_to_loginFragment, null);
    }


    public void onClickLogin(View view) {
        /*
        getActivity().getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.action_startFragment_to_loginFragment, LoginFragment.class, null)
                .commit();
                */
        final Bundle bundle = new Bundle();
        // bundle.putLong("fragment_start", null);

        NavHostFragment.findNavController(this).navigate(R.id.action_startFragment_to_loginFragment, null);

    }

    /*
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // binding.fragLoginBtn.setOnClickListener();
        binding = FragmentStartBinding.inflate()

    }
*/

}