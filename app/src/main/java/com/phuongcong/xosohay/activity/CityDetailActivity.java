package com.phuongcong.xosohay.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.phuongcong.xosohay.R;
import com.phuongcong.xosohay.webview.MyWebViewClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class CityDetailActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_detail);
        webView = (WebView) findViewById(R.id.webViewCityDetail);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setOnTouchListener(null);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        String link = "https://xoso.me/" + getIntent().getStringExtra("link");
        link = link.replace("https://xoso.me/https://xoso.me/", "https://xoso.me/");
        new CityDetailActivity.PareseURL().execute(new String[]{link});
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
                document.getElementsByClass("taskbar").get(0).remove();
                document.getElementsByClass("col-center").get(0).remove();
                document.getElementsByClass("txt-center ads").get(0).remove();
                int size = document.getElementsByClass("col-l").get(0).getElementsByClass("txt-center ads").size();
                for (int i = size - 1; i >= 0; i--) {
                    if (document.getElementsByClass("col-l").get(0).getElementsByClass("txt-center ads").get(i) != null)
                        document.getElementsByClass("col-l").get(0).getElementsByClass("txt-center ads").get(i).remove();
                }
                int size2 = document.getElementsByClass("col-l").get(0).getElementsByClass("cp-sms").size();
                for (int j = size2 - 1; j >= 0; j--) {
                    if (document.getElementsByClass("col-l").get(0).getElementsByClass("cp-sms").get(j) != null)
                        document.getElementsByClass("col-l").get(0).getElementsByClass("cp-sms").get(j).remove();
                }
                int size3 = document.getElementsByClass("col-l").get(0).getElementsByClass("see-more").size();
                for (int e = size3 - 1; e >= 0; e--) {
                    if (document.getElementsByClass("col-l").get(0).getElementsByClass("see-more").get(e) != null)
                        document.getElementsByClass("col-l").get(0).getElementsByClass("see-more").get(e).remove();
                }
                int size4 = document.getElementsByClass("col-l").get(0).getElementsByClass("title-bor clearfix kq-title").size();
                for (int f = size4 - 1; f >= 0; f--) {
                    if (document.getElementsByClass("col-l").get(0).getElementsByClass("title-bor clearfix kq-title").get(f) != null) {
                        if (document.getElementsByClass("col-l").get(0).getElementsByClass("title-bor clearfix kq-title").get(f).getElementsByTag("a").size() == 3) {
                            document.getElementsByClass("col-l").get(0).getElementsByClass("title-bor clearfix kq-title").get(f).getElementsByTag("a").get(1).remove();
                            document.getElementsByClass("col-l").get(0).getElementsByClass("title-bor clearfix kq-title").get(f).getElementsByTag("a").get(0).remove();
                        }
                    }
                }


                document.getElementsByClass("top-info").get(0).remove();
                document.getElementsByClass("txt-right pad5").get(0).remove();
                document.getElementsByClass("col-right").get(0).remove();
                document.getElementsByClass("footer").get(0).remove();
                document.getElementsByClass("link-du-doan").get(0).remove();
                document.getElementsByClass("box mo-thuong-ngay").get(0).remove();
                document.getElementById("nav").remove();
               /* jumbotrons.getElementsByTag("img").get(0).remove();
                Elements elements = jumbotrons.getElementsByTag("p");
                int sze = elements.size();
                jumbotrons.getElementsByTag("p").get(sze - 1).remove();
                jumbotrons.getElementsByTag("p").get(sze - 2).remove();
                jumbotrons.getElementsByTag("p").get(sze - 3).remove();
*/
                content = document.html().toString();
                content = content.replaceAll("xoso.me", "xo so hay");
                content = content.replaceAll("<a class=\"title-a\" href=", "<a class=\"title-a\" id=");
                content = content.replace("\"/css/css/style.min.css?v=", "\"https://xoso.me/css/css/style.min.css?v=");

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

