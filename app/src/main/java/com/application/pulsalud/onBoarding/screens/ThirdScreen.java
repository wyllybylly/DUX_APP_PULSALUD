package com.application.pulsalud.onBoarding.screens;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.pulsalud.R;
import com.application.pulsalud.services.StorageManager;

public class ThirdScreen extends Fragment {

    public ThirdScreen() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third_screen, container, false);

        ViewPager2 viewPager = requireActivity().findViewById(R.id.viewPager);

        view.findViewById(R.id.btn_next_third).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { viewPager.setCurrentItem(3); }
        });

        return view;

    }

}