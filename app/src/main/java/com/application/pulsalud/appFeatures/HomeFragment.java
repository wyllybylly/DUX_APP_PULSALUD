package com.application.pulsalud.appFeatures;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.application.pulsalud.R;
import com.application.pulsalud.databinding.FragmentHomeBinding;
import com.application.pulsalud.services.StorageManager;

import java.util.Calendar;

public class HomeFragment extends Fragment {

    public HomeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentHomeBinding homeBinding = FragmentHomeBinding.bind(view);

        EditText editName = homeBinding.editNombre;
        EditText editEdad = homeBinding.editEdad;
        EditText editSangre = homeBinding.editSangre;

        EditText editAlergia = homeBinding.editAlergia;
        EditText editEnfermedad = homeBinding.editEnfermedad;
        EditText editMedicamento = homeBinding.editMedicamento;

        TextView editVacunado = homeBinding.editVacunado;

        EditText editContactoNombre = homeBinding.editContactoNombre;
        EditText editContactoTel = homeBinding.editContactoTel;

        String codigo_qr = StorageManager.readQRCodeData(getContext());

        if (!codigo_qr.equals("EMPTY")){

            String[] tags = codigo_qr.split("\\|");

            editName.setText(tags[0].split(":")[1]);

            Calendar calendar = Calendar.getInstance();
            int year_actual = calendar.get(Calendar.YEAR);
            int year_birth = Integer.parseInt(tags[1].split(":")[1]);
            String edad = String.valueOf(year_actual-year_birth);

            editEdad.setText(edad);

            editVacunado.setText(tags[2].split(":")[1]);
            editSangre.setText(tags[3].split(":")[1]);
            editAlergia.setText(tags[4].split(":")[1]);
            editMedicamento.setText(tags[5].split(":")[1]);
            editEnfermedad.setText(tags[6].split(":")[1]);
            editContactoNombre.setText(tags[7].split(":")[1]);
            editContactoTel.setText(tags[8].split(":")[1]);

        }

        //Boton de volver al lector
        Button boton = homeBinding.btnReaderHome;

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StorageManager.writeQRCodeData(getContext(),"EMPTY");
                //Voy a la screen reader
                Bundle bundle = new Bundle();
                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.action_homeFragment_to_scannerFragment, bundle);
            }
        });

    }
}