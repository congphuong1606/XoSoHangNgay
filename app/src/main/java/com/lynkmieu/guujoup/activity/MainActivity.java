package com.lynkmieu.guujoup.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.lynkmieu.guujoup.R;

public class MainActivity extends AppCompatActivity {
    ImageView btnKQTH;
    private ImageView btnKQSX;

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
    }
}
