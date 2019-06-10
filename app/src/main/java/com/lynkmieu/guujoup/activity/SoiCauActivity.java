package com.lynkmieu.guujoup.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lynkmieu.guujoup.R;
import com.lynkmieu.guujoup.adapter.SoiCauAdapter;
import com.lynkmieu.guujoup.event.OnClickSoiCau;
import com.lynkmieu.guujoup.model.SoiCau;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class SoiCauActivity extends AppCompatActivity implements OnClickSoiCau {

    private RecyclerView rcvSoiCau;
    private ArrayList<SoiCau> soiCaus = new ArrayList<>();
    private SoiCauAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soi_cau);
        rcvSoiCau = (RecyclerView) findViewById(R.id.rcv_soi_cau);
        mAdapter = new SoiCauAdapter(soiCaus, this, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rcvSoiCau.setLayoutManager(mLayoutManager);
        rcvSoiCau.setItemAnimator(new DefaultItemAnimator());
        rcvSoiCau.setAdapter(mAdapter);
        new SoiCauActivity.PareseURL().execute(new String[]{"https://chotlo.com/soi-cau"});
    }

    @Override
    public void click(String link) {
        Intent intent = new Intent(SoiCauActivity.this, ChiTietSoiCauActivity.class);
        intent.putExtra("link", link);
        startActivity(intent);
    }

    private class PareseURL extends AsyncTask<String, Void, ArrayList<SoiCau>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected ArrayList<SoiCau> doInBackground(String... params) {
            ArrayList<SoiCau> soiCaus = new ArrayList<>();
            try {
                Document document = Jsoup.connect(params[0]).validateTLSCertificates(false).get();
                String sss = document.text();
                Elements jumbotrons = document.getElementsByClass("jumbotron");
                for (Element element : jumbotrons) {
                    String linkImage = element.getElementsByTag("img").get(0).absUrl("src");
                    String link = element.getElementsByTag("a").get(0).absUrl("href");
                    String title = element.getElementsByTag("a").get(0).text();
                    String descrition = element.getElementsByClass("news-content").get(0).text();
                    descrition = descrition.replaceAll("chotlo.com", "phuongcong");
                    SoiCau soiCau = new SoiCau(linkImage, title, descrition, link);
                    soiCaus.add(soiCau);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return soiCaus;
        }

        @Override
        protected void onPostExecute(ArrayList<SoiCau> s) {
            super.onPostExecute(s);

            if (s != null) {
                soiCaus.addAll(s);
                mAdapter.notifyDataSetChanged();

            }


        }
    }
}
