package com.application.pulsalud.appFeatures;

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
import android.widget.TextView;

import com.application.pulsalud.R;
import com.application.pulsalud.databinding.FragmentVisualizerBinding;


public class Visualizer extends Fragment {

    FragmentVisualizerBinding binding;

    public Visualizer() { }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_visualizer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView covid = binding.editVacunado;

        covid.setText("SI");

        Button boton = binding.btnReaderHome;

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.action_visualizer_to_homeFragment, bundle);
            }
        });

    }


}