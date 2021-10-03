package com.application.pulsalud;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.application.pulsalud.services.StorageManager;

public class SplashFragment extends Fragment {

    public SplashFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final Handler handler = new Handler();

        handler.postDelayed(() -> {

            Bundle bundle = new Bundle();
            NavController navController = Navigation.findNavController(requireView());

            Boolean validate = onBoardingFinished();

            if (validate) {
                navController.navigate(R.id.action_splashFragment_to_scannerFragment, bundle);
            } else {
                navController.navigate(R.id.action_splashFragment_to_viewPagerFragment, bundle);
            }
        }, 3000);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    private Boolean onBoardingFinished(){
        return StorageManager.readFlagOnBoarding(getContext());
    }

}