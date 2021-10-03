package com.application.pulsalud.onBoarding.screens;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.application.pulsalud.R;
import com.application.pulsalud.databinding.FragmentSixthScreenBinding;
import com.application.pulsalud.services.StorageManager;

public class SixthScreen extends Fragment {

    public SixthScreen() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sixth_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentSixthScreenBinding binding = FragmentSixthScreenBinding.bind(view);

        Button btnCreateProfile = binding.btnCreateProfileInit;
        Button btnReaderCode = binding.btnReaderInit;

        btnCreateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.action_viewPagerFragment_to_profileFragment, bundle);
                onBoardingFinished();
            }
        });

        btnReaderCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.action_viewPagerFragment_to_scannerFragment, bundle);
                onBoardingFinished();
            }
        });

    }

    private void onBoardingFinished(){
        StorageManager.writeFlagOnBoarding(getContext(), true);
    }

}