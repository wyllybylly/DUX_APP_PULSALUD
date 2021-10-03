package com.application.pulsalud.onBoarding;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.application.pulsalud.R;
import com.application.pulsalud.onBoarding.screens.FifthScreen;
import com.application.pulsalud.onBoarding.screens.FirstScreen;
import com.application.pulsalud.onBoarding.screens.FourthScreen;
import com.application.pulsalud.onBoarding.screens.SecondScreen;
import com.application.pulsalud.onBoarding.screens.SixthScreen;
import com.application.pulsalud.onBoarding.screens.ThirdScreen;

import java.util.ArrayList;

public class ViewPagerFragment extends Fragment {

    public ViewPagerFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);

        ArrayList<Fragment> fragment_list = new ArrayList<>();

        FirstScreen first = new FirstScreen();
        SecondScreen second = new SecondScreen();
        ThirdScreen third = new ThirdScreen();
        FourthScreen fourth = new FourthScreen();
        FifthScreen fifth = new FifthScreen();
        SixthScreen sixth = new SixthScreen();

        fragment_list.add(first);
        fragment_list.add(second);
        fragment_list.add(third);
        fragment_list.add(fourth);
        fragment_list.add(fifth);
        fragment_list.add(sixth);

        ViewPagerAdapter adapter = new ViewPagerAdapter(
                requireActivity().getSupportFragmentManager(),
                getLifecycle(),
                fragment_list
        );

        ViewPager2 vpPager = view.findViewById(R.id.viewPager);
        vpPager.setAdapter(adapter);

        return view;

    }
}