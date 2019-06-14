package com.phuongcong.xosohay.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.phuongcong.xosohay.R;
import com.phuongcong.xosohay.adapter.MenuItemAdapter;
import com.phuongcong.xosohay.adapter.SomoAdapter;
import com.phuongcong.xosohay.event.OnClickMenuItem;
import com.phuongcong.xosohay.model.ItemMenu;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cn.iwgang.countdownview.CountdownView;

public class MainActivity extends AppCompatActivity implements OnClickMenuItem {
    ImageView btnKQTH;
    private ImageView btnKQSX;
    private String timeCurent;
    private String dayCurent;
    private RecyclerView rcv1;
    private RecyclerView rcv2;
    private ArrayList<ItemMenu> menu1s = new ArrayList<>();
    private ArrayList<ItemMenu> menu2s = new ArrayList<>();
    private MenuItemAdapter mAdapter1;
    private MenuItemAdapter mAdapter2;
    private CountdownView mCvCountdownView;
    private CountdownView cvB;
    private CountdownView cvT;
    private CountdownView cvN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeCurent = getCurrentHour();
        dayCurent = getCurrentDay();
        findID();
        setAdapter();
        setMenuIntoScreen();
        setOnClick();
    }

    public String getCurrentDay() {
       /* SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
        Calendar calendar = Calendar.getInstance();
        return dayFormat.format(calendar.getTime());*/
        Date mydate = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
        String hour = dateFormat.format(mydate);
        return hour;
    }
    public String getCurrentHour() {
        Date mydate = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String hour = dateFormat.format(mydate);
        return hour;
    }


    private void setMenuIntoScreen() {
        menu1s.add(new ItemMenu("Dự đoán kết quả", R.drawable.soicau, "SoiCau"));
        menu1s.add(new ItemMenu("Một giấc ngủ ngon", R.drawable.so_mo, "SoMo"));
        menu1s.add(new ItemMenu("Thày đã bảo rồi", R.drawable.thayphan, "ThayPhan"));
        menu1s.add(new ItemMenu("Con số hôm nay", R.drawable.consohomnay, "SoDep"));
        menu2s.add(new ItemMenu("Vietlott – Max 4D", R.drawable.max4d, "Max4d"));
        menu2s.add(new ItemMenu("Vietlott – Mega 6/45", R.drawable.mega645, "Mega645"));
        menu2s.add(new ItemMenu("Vietlott – Power 655", R.drawable.power655, "Power655"));
        menu2s.add(new ItemMenu("Vietlott – Max 3D", R.drawable.max3d, "Max3d"));


        mAdapter1.notifyDataSetChanged();
        mAdapter2.notifyDataSetChanged();
    }

    @Override
    public void click(String menuItem) {
        Intent intent = null;
        switch (menuItem) {
            case "SoiCau":
                startActivity(new Intent(MainActivity.this, SoiCauActivity.class));
                break;
            case "SoMo":
                startActivity(new Intent(MainActivity.this, SoMoActivity.class));
                break;
            case "ThayPhan":
                startActivity(new Intent(MainActivity.this, ThayPhanActivity.class));
                break;
            case "SoDep":
                startActivity(new Intent(MainActivity.this, SoDepHomNay.class));
                break;
            case "Max4d":
                intent = new Intent(MainActivity.this, VietLott4DActivity.class);
                intent.putExtra("type", menuItem);
                startActivity(intent);
                break;
            case "Mega645":
                intent = new Intent(MainActivity.this, VietLott4DActivity.class);
                intent.putExtra("type", menuItem);
                startActivity(intent);
                break;
            case "Power655":
                intent = new Intent(MainActivity.this, VietLott4DActivity.class);
                intent.putExtra("type", menuItem);
                startActivity(intent);
                break;
            case "Max3d":
                break;

        }
    }

    private void setAdapter() {
        mAdapter1 = new MenuItemAdapter(MainActivity.this, menu1s, this);
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rcv1.setLayoutManager(horizontalLayoutManagaer);
        rcv1.setItemAnimator(new DefaultItemAnimator());
        rcv1.setAdapter(mAdapter1);

        mAdapter2 = new MenuItemAdapter(MainActivity.this, menu2s, this);
        LinearLayoutManager horizontalLayoutManagaer2 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rcv2.setLayoutManager(horizontalLayoutManagaer2);
        rcv2.setItemAnimator(new DefaultItemAnimator());
        rcv2.setAdapter(mAdapter2);
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
      /*  btnSoMo.setOnClickListener(new View.OnClickListener() {
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
        });*/
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
        rcv1 = (RecyclerView) findViewById(R.id.rcv1);
        rcv2 = (RecyclerView) findViewById(R.id.rcv2);
        mCvCountdownView = (CountdownView) findViewById(R.id.cv_countdownView);
        cvB = (CountdownView) findViewById(R.id.cv_bac);
        cvN = (CountdownView) findViewById(R.id.cv_nam);
        cvT = (CountdownView) findViewById(R.id.cv_trung);
        mCvCountdownView.start(22 * 60 * 60 * 1000);
        cvB.start(20 * 60 * 30 * 1000);
        cvN.start(15 * 45 * 34 * 1000);
        cvT.start(18 * 30 * 25 * 1000);
    }

}
