package com.lynkmieu.guujoup.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lynkmieu.guujoup.R;
import com.lynkmieu.guujoup.adapter.SomoAdapter;
import com.lynkmieu.guujoup.adapter.TinhThanhAdapter;
import com.lynkmieu.guujoup.model.SoMo;
import com.lynkmieu.guujoup.model.Story;
import com.lynkmieu.guujoup.model.dto.Result;
import com.lynkmieu.guujoup.network.ApiService;
import com.lynkmieu.guujoup.network.ApiUtils;
import com.lynkmieu.guujoup.utils.Database;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SoMoActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ArrayList<SoMo> somos = new ArrayList<>();
    private ArrayList<SoMo> listSeach = new ArrayList<>();
    private SomoAdapter mAdapter;
    private TextView editText;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_mo);
        recyclerView = (RecyclerView) findViewById(R.id.rcv_somo);
        editText = (EditText) findViewById(R.id.edit_search_so_mo);

        mAdapter = new SomoAdapter(somos, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        readData();
        recyclerView.setAdapter(mAdapter);
        seach();

    }

    private void seach() {
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
                return false;
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mAdapter.filter(charSequence.toString().trim(), listSeach);
                recyclerView.invalidate();
                recyclerView.smoothScrollToPosition(0);
                if (charSequence.length() == 0) {
                    editText.setFocusable(false);
                    editText.setFocusableInTouchMode(false);
                    View view = getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void readData() {
        SQLiteDatabase database = Database.initDatabase(this, "somo.db");
        Cursor cursor = database.rawQuery("select * from somo", null);
        somos.clear();
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {

                    int id = cursor.getInt(0);
                    String ten = cursor.getString(1);
                    String so = cursor.getString(2);
                    somos.add(new SoMo(id + "", ten, so));
                } while (cursor.moveToNext());
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        listSeach.addAll(somos);
        mAdapter.notifyDataSetChanged();
        recyclerView.smoothScrollToPosition(0);


    }


}
