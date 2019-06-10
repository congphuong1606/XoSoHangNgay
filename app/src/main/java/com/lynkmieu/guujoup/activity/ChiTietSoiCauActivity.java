package com.lynkmieu.guujoup.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.lynkmieu.guujoup.R;
import com.lynkmieu.guujoup.webview.MyWebViewClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ChiTietSoiCauActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_soi_cau);
        webView = (WebView) findViewById(R.id.webViewSoiCau);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setOnTouchListener(null);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        new ChiTietSoiCauActivity.PareseURL().execute(new String[]{getIntent().getStringExtra("link")});
    }

    private class PareseURL extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            String content = "";
            try {
                Document document = Jsoup.connect(params[0]).validateTLSCertificates(false).get();
                Element jumbotrons = document.getElementById("div_detail");
                jumbotrons.getElementsByTag("img").get(0).remove();
                Elements elements =jumbotrons.getElementsByTag("p");
                int sze=elements.size();
                jumbotrons.getElementsByTag("p").get(sze-1).remove();
                jumbotrons.getElementsByTag("p").get(sze-2).remove();
                jumbotrons.getElementsByTag("p").get(sze-3).remove();

                content = jumbotrons.html().toString();
                content=content.replaceAll("Chốt Lô","PC");
                content=content.replaceAll("Chotlo.com","PC");

            } catch (IOException e) {
                e.printStackTrace();
            }

            return content;
        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);

            if (data != null) {
                webView.loadData(data, "text/html; charset=utf-8", "UTF-8");

            }


        }
    }
}
