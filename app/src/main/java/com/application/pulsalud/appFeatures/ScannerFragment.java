package com.application.pulsalud.appFeatures;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.application.pulsalud.R;
import com.application.pulsalud.services.StorageManager;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import es.dmoral.toasty.Toasty;

public class ScannerFragment extends Fragment {

    public ScannerFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scanner, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        readCode();

    }

    private void readCode() {
        IntentIntegrator intent = IntentIntegrator.forSupportFragment(ScannerFragment.this);
        intent.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intent.setPrompt("Escanear Código QR");
        intent.setCameraId(0);
        intent.setBeepEnabled(false);
        intent.setOrientationLocked(false);
        intent.setCaptureActivity(CapturePortrait.class);
        intent.setBarcodeImageEnabled(false);
        intent.initiateScan();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() != null) {
                String resultScan = result.getContents();

                if(!isNumeric(resultScan)) {
                    //Alamaceno el Códgio QR que he leido
                    StorageManager.writeQRCodeData(getContext(), resultScan);
                    //Voy a la screen que mostrará los datos
                    Bundle bundle = new Bundle();
                    NavController navController = Navigation.findNavController(requireView());
                    navController.navigate(R.id.action_scannerFragment_to_homeFragment, bundle);
                }else{
                    Toasty.error(requireContext(), "No se puede leer el Código QR. Intente nuevamente.", Toast.LENGTH_LONG).show();
                    getParentFragmentManager().beginTransaction().detach(this).attach(this).commit();
                }
            } else {
                Toasty.error(requireContext(), "No se puede leer el Código QR.", Toast.LENGTH_LONG).show();
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}