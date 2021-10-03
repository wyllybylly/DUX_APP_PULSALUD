package com.application.pulsalud.services;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class StorageManager {

    private static final String FIRST_TIME_INIT_APP = "First Time";
    private static final String QR_CODE_DATA = "QR";

    public static void writeFlagOnBoarding(android.content.Context context, Boolean flag_onBoarding){
        SharedPreferences pref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(FIRST_TIME_INIT_APP, flag_onBoarding);
        editor.apply();
    }

    public static Boolean readFlagOnBoarding(android.content.Context context){
        SharedPreferences pref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getBoolean(FIRST_TIME_INIT_APP, false);
    }

    public static void writeQRCodeData(android.content.Context context, String qr_code_data){
        SharedPreferences pref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(QR_CODE_DATA, qr_code_data);
        editor.apply();
    }

    public static String readQRCodeData(android.content.Context context){
        SharedPreferences pref = androidx.preference.PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getString(QR_CODE_DATA, "EMPTY");
    }

}
