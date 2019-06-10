package com.lynkmieu.guujoup.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lynkmieu.guujoup.R;
import com.lynkmieu.guujoup.model.SoiCau;
import com.lynkmieu.guujoup.utils.Database;

public class MainActivity extends AppCompatActivity {
    ImageView btnKQTH;
    private ImageView btnKQSX;
    private ImageView btnSoMo;
    private ImageView btnSoHomnay;
    private ImageView btnThayPhan;
    private ImageView btnSoiCau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findID();
        setOnClick();
    }

    private void setOnClick() {
        btnKQTH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KQTHActivity.class);
                startActivity(intent);
            }
        });
        btnKQSX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SXKQActivity.class);
                startActivity(intent);
            }
        });
        btnSoMo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SoMoActivity.class);
                startActivity(intent);
            }
        });
        btnSoHomnay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SoDepHomNay.class);
                startActivity(intent);
            }
        });
        btnThayPhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThayPhanActivity.class);
                startActivity(intent);
            }
        });
        btnSoiCau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SoiCauActivity.class);
                startActivity(intent);
            }
        });
    }

/*    private void startAc(int i) {

        switch (i) {
            case 1:

                break;
            case 2:
                break;
            break;

        }
    }*/

    private void findID() {
        btnKQTH = (ImageView) findViewById(R.id.btn_tinhthanh);
        btnKQSX = (ImageView) findViewById(R.id.btn_kqsx);
        btnSoMo = (ImageView) findViewById(R.id.btn_so_mo);
        btnSoHomnay = (ImageView) findViewById(R.id.btn_so_homnay);
        btnThayPhan = (ImageView) findViewById(R.id.btn_thay_phan);
        btnSoiCau = (ImageView) findViewById(R.id.btn_soicau);
    }
}
