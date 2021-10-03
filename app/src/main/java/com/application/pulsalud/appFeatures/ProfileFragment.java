package com.application.pulsalud.appFeatures;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.pulsalud.R;
import com.application.pulsalud.databinding.FragmentProfileBinding;
import com.application.pulsalud.services.StorageManager;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class ProfileFragment extends Fragment {

    FileOutputStream outputStream;
    Bitmap bitmapQrCode;

    public ProfileFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentProfileBinding binding = FragmentProfileBinding.bind(view);

        EditText etInput1 = binding.editTextTextPersonName;
        EditText etInput2 = binding.editTextTextPersonApellido;
        EditText etInput3 = binding.editTextTextPersonAlergias;

        EditText etInput4 = binding.editPersonAnio;
        EditText etInput5 = binding.editPersonCovid;
        EditText etInput6 = binding.editPersonEnfermedad;

        EditText etInput7 = binding.editPersonMedicamentos;
        EditText etInput8 = binding.editPersonSangre;

        EditText etInput9 = binding.editPersonContactName;
        EditText etInput10 = binding.editPersonContactPhone;

        Button btGenerate = binding.btnGenerateQrCode;

        btGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //GENERATE QR CODE
                //Get value from Edit Text
                String sTextName = etInput1.getText().toString().trim();
                String sTextLastName = etInput2.getText().toString().trim();
                String sTextAlergia = etInput3.getText().toString().trim();

                String sTextAnio = etInput4.getText().toString().trim();
                String sTextCovid = etInput5.getText().toString().trim();
                String sTextEnferm = etInput6.getText().toString().trim();

                String sTextMedic = etInput7.getText().toString().trim();
                String sTextSangre = etInput8.getText().toString().trim();
                String sTextContactName = etInput9.getText().toString().trim();
                String sTextContactPhone = etInput10.getText().toString().trim();

                if (!sTextName.isEmpty() && !sTextLastName.isEmpty() && !sTextAnio.isEmpty() && !sTextCovid.isEmpty() && !sTextSangre.isEmpty()){

                    String full_data = "Name:" + sTextName + " " + sTextLastName + "|" + "YearBirth:" + sTextAnio + "|" + "CovidVac:" + sTextCovid + "|" + "Blood:" + sTextSangre + "|" + "Allergy:" + sTextAlergia + "|" + "Medicines:" + sTextMedic + "|" + "Illnes:" + sTextEnferm + "|" + "ContactName:" + sTextContactName + "|" + "ContactPhone:" + sTextContactPhone;

                    //Guardo los datos para generar el codigo QR en otra pantalla
                    StorageManager.writeQRCodeData(getContext(), full_data);

                    Toasty.success(requireContext(), "Código QR generado correctamente.", Toast.LENGTH_LONG).show();

                    Bundle bundle = new Bundle();
                    NavController navController = Navigation.findNavController(requireView());
                    navController.navigate(R.id.action_profileFragment_to_codeViewer, bundle);

                }else{
                    Toasty.error(requireContext(), "Complete los campos requeridos.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}