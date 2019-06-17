package com.phuongcong.xosohay.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.phuongcong.xosohay.R;

import java.text.SimpleDateFormat;

public class SoDepHomNay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_dep_hom_nay);

        String android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        String day = dateFormat.format(System.currentTimeMillis());
        Toast.makeText(SoDepHomNay.this, "Số đẹp của bạn là:  " + columnCharToNumber(android_id) + day, Toast.LENGTH_LONG).show();
    }


    public static int columnCharToNumber(String str) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (str.length() == 1) {
            return alphabet.indexOf(str);
        }
        if (str.length() == 2) {
            return (alphabet.indexOf(str.substring(1)) + 26 * (1 + alphabet.indexOf(str.substring(0, 1))));
        }
        return -1;
    }
}
