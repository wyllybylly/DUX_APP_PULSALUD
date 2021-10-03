package com.application.pulsalud.onBoarding.screens;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.pulsalud.R;

public class FourthScreen extends Fragment {

    public FourthScreen() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fourth_screen, container, false);

        ViewPager2 viewPager = requireActivity().findViewById(R.id.viewPager);

        view.findViewById(R.id.btn_next_fourth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { viewPager.setCurrentItem(4); }
        });

        return view;

    }
}