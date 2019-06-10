package com.lynkmieu.guujoup.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lynkmieu.guujoup.R;
import com.lynkmieu.guujoup.model.SoMo;
import com.lynkmieu.guujoup.model.ThayPhan;
import com.lynkmieu.guujoup.utils.Database;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ThayPhanActivity extends AppCompatActivity {

    private TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_phan);
        tvContent = (TextView) findViewById(R.id.tv_content);
        new ThayPhanActivity.PareseURL().execute(new String[]{"https://xosolocphat.com/soi-cau-mien-phi.html"});
    }

    private class PareseURL extends AsyncTask<String, Void, ThayPhan> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected ThayPhan doInBackground(String... params) {
            ThayPhan soThayPhan = new ThayPhan();
            String bachthu = "";
            String bachthu2 = "";
            try {
                Document document = Jsoup.connect(params[0]).get();

                Elements Ptags = document.getElementsByClass("rectangle-speech-border").get(0).getElementsByTag("p");
                bachthu = Ptags.text();
                bachthu2 = Ptags.get(0).text();
                soThayPhan.setLotobachthu(Ptags.text());
                soThayPhan.setLotobachthu(Ptags.text());

            } catch (IOException e) {
                e.printStackTrace();
            }

            return soThayPhan;
        }

        @Override
        protected void onPostExecute(ThayPhan s) {
            super.onPostExecute(s);

            if (s != null) {
                tvContent.setText(s.getLotobachthu());
            }


        }
    }
}