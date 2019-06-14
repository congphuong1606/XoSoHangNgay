package com.phuongcong.xosohay.activity;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.phuongcong.xosohay.R;
import com.phuongcong.xosohay.webview.MyWebViewClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class VietLott4DActivity extends AppCompatActivity {

    private WebView webView;
    private String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viet_lott4_d);
        webView = (WebView) findViewById(R.id.webViewMax4D);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setOnTouchListener(null);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        if(getIntent().getStringExtra("type").equals("Max4d")){
            link = "https://xoso.me/kqxs-mega-645-ket-qua-xo-so-mega-6-45-vietlott-ngay-hom-nay.html";
        };
        if(getIntent().getStringExtra("type").equals("Mega645")){
            link = "https://xoso.me/kqxs-mega-645-ket-qua-xo-so-mega-6-45-vietlott-ngay-hom-nay.html";
        }; if(getIntent().getStringExtra("type").equals("Power655")){
            link = "https://xoso.me/kqxs-power-6-55-ket-qua-xo-so-power-6-55-vietlott-ngay-hom-nay.html";
        };

        new VietLott4DActivity.PareseURL().execute(new String[]{link});
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
                if (document.getElementsByClass("taskbar").get(0) != null)
                    document.getElementsByClass("taskbar").get(0).remove();
                if (document.getElementsByClass("col-center").get(0) != null)
                    document.getElementsByClass("col-center").get(0).remove();
                if (document.getElementsByClass("txt-center ads").get(0) != null)
                    document.getElementsByClass("txt-center ads").get(0).remove();
                if (document.getElementsByClass("paging txt-center magb10").get(0) != null)
                    document.getElementsByClass("paging txt-center magb10").get(0).remove();
                int si = document.getElementsByClass("col-l").get(0).getElementsByClass("box").size();
                if (document.getElementsByClass("col-l").get(0).getElementsByClass("box") != null)
                    document.getElementsByClass("col-l").get(0).getElementsByClass("box").get(si-1).remove();
                    document.getElementsByClass("col-l").get(0).getElementsByClass("box").get(si-2).remove();
                int size = document.getElementsByClass("col-l").get(0).getElementsByClass("txt-center ads").size();
                for (int i = size - 1; i >= 0; i--) {
                    if (document.getElementsByClass("col-l").get(0).getElementsByClass("txt-center ads").get(i) != null)
                        document.getElementsByClass("col-l").get(0).getElementsByClass("txt-center ads").get(i).remove();
                }
              /* if (document.getElementsByClass("col-l").get(0).getElementsByClass("box") != null){
                     size = document.getElementsByClass("col-l").get(0).getElementsByClass("cp-sms").size();
                }
                for (int j = size - 1; j >= 0; j--) {
                    if (document.getElementsByClass("col-l").get(0).getElementsByClass("cp-sms").get(j) != null)
                        document.getElementsByClass("col-l").get(0).getElementsByClass("cp-sms").get(j).remove();
                }
                int size3 = document.getElementsByClass("col-l").get(0).getElementsByClass("see-more").size();
                for (int e = size3 - 1; e >= 0; e--) {
                    if (document.getElementsByClass("col-l").get(0).getElementsByClass("see-more").get(e) != null)
                        document.getElementsByClass("col-l").get(0).getElementsByClass("see-more").get(e).remove();
                }*/
               /* int size4 = document.getElementsByClass("col-l").get(0).getElementsByClass("title-bor bold").size();
                for (int f = size4 - 1; f >= 0; f--) {
                    if (document.getElementsByClass("col-l").get(0).getElementsByClass("title-bor bold").get(f) != null) {
                        if (document.getElementsByClass("col-l").get(0).getElementsByClass("title-bor bold").get(f).getElementsByTag("a").size() == 2) {
                            document.getElementsByClass("col-l").get(0).getElementsByClass("title-bor bold").get(f).getElementsByTag("a").get(1).remove();
                            document.getElementsByClass("col-l").get(0).getElementsByClass("title-bor clearfix kq-title").get(f).getElementsByTag("a").get(0).remove();
                        }
                    }
                }*/

                if (document.getElementsByClass("top-info").get(0) != null)
                    document.getElementsByClass("top-info").get(0).remove();
                if (document.getElementsByClass("linkway").get(0) != null)
                    document.getElementsByClass("linkway").get(0).remove();
                if (document.getElementsByClass("col-right").get(0) != null)
                    document.getElementsByClass("col-right").get(0).remove();
                if (document.getElementsByClass("footer").get(0) != null)
                    document.getElementsByClass("footer").get(0).remove();
                if (document.getElementById("nav") != null)
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
                content = content.replaceAll("<a href=", "<a id=");
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
               /* Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {

                    }
                }, 1000);*/


            }


        }
    }
}
