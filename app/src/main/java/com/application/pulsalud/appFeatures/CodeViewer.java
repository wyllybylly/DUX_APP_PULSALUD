package com.application.pulsalud.appFeatures;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.pulsalud.R;
import com.application.pulsalud.databinding.FragmentCodeViewerBinding;
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
import java.io.OutputStream;
import java.util.Objects;

import es.dmoral.toasty.Toasty;

public class CodeViewer extends Fragment {

    Bitmap bitmapQrCode;
    ImageView imgQRCode;
    Button btn_save;
    Button btn_cancel;
    OutputStream outputStream;
    FragmentCodeViewerBinding binding;

    public CodeViewer() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_code_viewer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String full_data = StorageManager.readQRCodeData(getContext());
        binding = FragmentCodeViewerBinding.bind(view);

        btn_save = binding.btnSaveQrCodeView;
        btn_cancel = binding.btnCancelSaveCode;
        imgQRCode = binding.imgQrCode;

        if(!full_data.equals("EMPTY")) {
            FragmentCodeViewerBinding binding = FragmentCodeViewerBinding.bind(view);
            TextView textView = binding.textView8;
            MultiFormatWriter writer = new MultiFormatWriter();
            try {
                //Initialize bit matrix
                BitMatrix matrix = writer.encode(full_data, BarcodeFormat.QR_CODE, 1350, 1350);
                //Initialize barcode encoder
                BarcodeEncoder encoder = new BarcodeEncoder();
                //Initialize bitmap
                bitmapQrCode = encoder.createBitmap(matrix);
                //Set bitmap on image view
                imgQRCode.setImageBitmap(bitmapQrCode);
                //Initialize input manager
                InputMethodManager manager = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                //Hide soft keyboard
                manager.hideSoftInputFromWindow(textView.getApplicationWindowToken(), 0);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }else{
            Toasty.error(requireContext(), "No hay Código QR para mostrar.", Toast.LENGTH_LONG).show();
        }

        /*btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.success(requireContext(), "Código QR guardado.", Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.action_codeViewer_to_homeFragment, bundle);
            }
        });*/

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BitmapDrawable drawable = (BitmapDrawable) imgQRCode.getDrawable();

                if (drawable!=null) {
                    Bitmap bitmap = (Bitmap) drawable.getBitmap();

                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q){

                        File filepath = Environment.getExternalStorageDirectory();
                        File dir = new File(filepath.getAbsolutePath() + "/Demo/");
                        dir.mkdir();
                        File file = new File(dir, System.currentTimeMillis() + ".jpg");
                        try {
                            outputStream = new FileOutputStream(file);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        if (outputStream != null) {
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                            Toasty.success(requireContext(), "Código QR Grabado", Toast.LENGTH_LONG).show();

                            try {
                                outputStream.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                outputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toasty.error(requireContext(), "Error al Grabar el Código QR. Intentelo nuevamente.", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        saveImage(bitmap);
                    }
                }else{
                    Toasty.error(requireContext(), "Error al Grabar el Código QR. Intentelo nuevamente.", Toast.LENGTH_LONG).show();
                }

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.error(requireContext(), "Generación Cancelada.", Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                NavController navController = Navigation.findNavController(requireView());
                navController.navigate(R.id.action_codeViewer_to_profileFragment, bundle);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void saveImage(Bitmap bitmap) {

        try {
            ContentResolver resolver = requireContext().getContentResolver();
            ContentValues values = new ContentValues();
            values.put(MediaStore.MediaColumns.DISPLAY_NAME,"Image_"+".jpg");
            values.put(MediaStore.MediaColumns.MIME_TYPE,"image/jpg");
            values.put(MediaStore.MediaColumns.RELATIVE_PATH,Environment.DIRECTORY_PICTURES+File.separator+"TestFolderQRCodes");
            Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
            OutputStream outputStream = resolver.openOutputStream(imageUri);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            Toasty.success(requireContext(), "Código QR Grabado", Toast.LENGTH_LONG).show();

            StorageManager.writeQRCodeData(getContext(),"EMPTY");

            //Voy a la screen reader
            Bundle bundle = new Bundle();
            NavController navController = Navigation.findNavController(requireView());
            navController.navigate(R.id.action_codeViewer_to_scannerFragment, bundle);

        } catch (FileNotFoundException e) {
            Toasty.error(requireContext(), "Error al Grabar el Código QR. Intentelo nuevamente.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}