package com.phuongcong.xosohay.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.phuongcong.xosohay.R;
import com.phuongcong.xosohay.model.ThayPhan;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ThayPhanActivity extends AppCompatActivity {

    private WebView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_phan);
        tvContent = (WebView) findViewById(R.id.tv_content);
        tvContent.setBackgroundColor(0);
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
             /*   soThayPhan.setLotobachthu(Ptags.get(0).text().split(" , ")[0]);
                soThayPhan.setLot(Ptags.get(0).text().split(" , ")[1]);
                soThayPhan.setThamkhaodacbiet(Ptags.get(1).text().split(" , ")[0]);
                soThayPhan.setDau(Ptags.get(1).text().split(" , ")[1]);
                soThayPhan.setDuoi(Ptags.get(1).text().split(" , ")[2]);
                soThayPhan.setCauamduongnguhanh(Ptags.get(2).text());
                soThayPhan.setXien(Ptags.get(3).text());*/
                String html = Ptags.html().toString();
                html=html.replaceAll("<strong>","<br><strong>");
                html=html.replaceAll("</cite>","</cite></p>");
                html=html.replaceAll("Căn số do duyên","<br><br><p style=\"text-align: center;\">Căn số do duyên");
                html=html.replaceAll("duyên - có","duyên <br> có");
                html=html.replaceAll("được - thông","được <br> thông");
                html=html.replaceAll("<cite>","<cite><br>");
                html="<body style=\"background-color: rgba(10,10,10,0.5);padding: 15px; height: 260px; border-radius:15px!important;\">"+html+"<style>.dvs-bigger {\n" +
                        "    font-weight: 700;\n" +
                        "    color: red;\n" +
                        "    background: #ff0;\n" +
                        "} .quote {\n" +
                        "    font-family: Georgia,serif;\n" +
                        "    font-size: 120%;\n" +
                        "    float: left;\n" +
                        "    font-style: italic;\n" +
                        "    margin: .25em 0;\n" +
                        "    padding: 1em 25px;\n" +
                        "    position: relative;\n" +
                        "}</style></body>";

                soThayPhan.setCaplo(html);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return soThayPhan;
        }

        @Override
        protected void onPostExecute(ThayPhan s) {
            super.onPostExecute(s);

            if (s != null) {
                // tvContent.setText(s.getLotobachthu());
                tvContent.loadData(s.getCaplo(), "text/html; charset=utf-8", "UTF-8");
            }


        }
    }
}