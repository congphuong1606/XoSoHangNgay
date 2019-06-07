package com.lynkmieu.guujoup.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;

import com.lynkmieu.guujoup.R;
import com.lynkmieu.guujoup.adapter.TinhThanhAdapter;
import com.lynkmieu.guujoup.model.TinhThanh;
import com.lynkmieu.guujoup.webview.MyWebViewClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class KQTHActivity extends AppCompatActivity {
    ArrayList<TinhThanh> TINHTHANHS = new ArrayList<TinhThanh>();
    public static final String MY_URL = "https://xoso.me/embedded/kq-mienbac";
    private WebView webView;

    private RecyclerView recyclerView;
    private TinhThanhAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kqth);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new TinhThanhAdapter(TINHTHANHS);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        setlistTinhThanh();

    }

    private void setlistTinhThanh() {
        TINHTHANHS.add(new TinhThanh("Hải Phòng", "hai-phong"));
        TINHTHANHS.add(new TinhThanh("Quảng Ninh", "quang-ninh"));
        TINHTHANHS.add(new TinhThanh("Bắc Ninh", "bac-ninh"));
        TINHTHANHS.add(new TinhThanh("Nam Định", "nam-dinh"));
        TINHTHANHS.add(new TinhThanh("Thái Bình ", "thai-binh"));
        TINHTHANHS.add(new TinhThanh("Gia Lai", "gia-lai"));
        TINHTHANHS.add(new TinhThanh("Ninh Thuận", "ninh-thuan"));
        TINHTHANHS.add(new TinhThanh("Phú Yên", "phu-yen"));
        TINHTHANHS.add(new TinhThanh("Thừa Thiên Huế", "thua-thien-hue"));
        TINHTHANHS.add(new TinhThanh("Đắc Lắc", "dac-lac"));
        TINHTHANHS.add(new TinhThanh("Quảng Nam", "quang-nam"));
        TINHTHANHS.add(new TinhThanh("Bình Định", "binh-dinh"));
        TINHTHANHS.add(new TinhThanh("Quảng Bình", "quang-binh"));
        TINHTHANHS.add(new TinhThanh("Quảng Trị", "quang-tri"));
        TINHTHANHS.add(new TinhThanh("Đắc Nông", "dac-nong"));
        TINHTHANHS.add(new TinhThanh("Quảng Ngãi", "quang-ngai"));
        TINHTHANHS.add(new TinhThanh("Kon Tum", "kon-tum"));
        TINHTHANHS.add(new TinhThanh("Đà Nẵng", "da-nang"));
        TINHTHANHS.add(new TinhThanh("Khánh Hòa", "khanh-hoa"));
        TINHTHANHS.add(new TinhThanh("Hà Nội", "ha-noi"));
        TINHTHANHS.add(new TinhThanh("Bình Dương", "binh-duong"));
        TINHTHANHS.add(new TinhThanh("Trà Vinh", "tra-vinh"));
        TINHTHANHS.add(new TinhThanh("Vĩnh Long", "vinh-long"));
        TINHTHANHS.add(new TinhThanh("Cà Mau", "ca-mau"));
        TINHTHANHS.add(new TinhThanh("Đồng Tháp", "dong-thap"));
        TINHTHANHS.add(new TinhThanh("Bạc Liêu", "bac-lieu"));
        TINHTHANHS.add(new TinhThanh("Bến Tre", "ben-tre"));
        TINHTHANHS.add(new TinhThanh("Vũng Tàu", "vung-tau"));
        TINHTHANHS.add(new TinhThanh("Cần Thơ", "can-tho"));
        TINHTHANHS.add(new TinhThanh("Đồng Nai", "dong-nai"));
        TINHTHANHS.add(new TinhThanh("Sóc Trăng", "soc-trang"));
        TINHTHANHS.add(new TinhThanh("An Giang", "an-giang"));
        TINHTHANHS.add(new TinhThanh("Bình Thuận", "binh-thuan"));
        TINHTHANHS.add(new TinhThanh("Tây Ninh", "tay-ninh"));
        TINHTHANHS.add(new TinhThanh("Bình Phước", "binh-phuoc"));
        TINHTHANHS.add(new TinhThanh("Hậu Giang", "hau-giang"));
        TINHTHANHS.add(new TinhThanh("Long An", "long-an"));
        TINHTHANHS.add(new TinhThanh("Đà Lạt", "da-lat"));
        TINHTHANHS.add(new TinhThanh("Kiên Giang", "kien-giang"));
        TINHTHANHS.add(new TinhThanh("Tiền Giang", "tien-giang"));
        TINHTHANHS.add(new TinhThanh("TP Hồ Chí Minh", "thanh-pho-ho-chi-minh"));
        mAdapter.notifyDataSetChanged();
    }

    private void goURl() {

        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        //webView.loadUrl(MY_URL);
    }

    private void configRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 3);
        //  recycler.hasFixedSize();
        //  recycler.setLayoutManager(layoutManager);
    }

    //Download HTML bằng AsynTask
    private class DownloadTask extends AsyncTask<String, Void, String> {

        private static final String TAG = "DownloadTask";

        @Override
        protected String doInBackground(String... strings) {
            Document document = null;
            String staticContent = "";
            try {
                document = (Document) Jsoup.connect(strings[0]).get();
                if (document != null) {
                    Element kq = document.getElementById("load_kq_mb_0");
                    staticContent = document.toString() + "";
                    staticContent = staticContent.replaceAll("Ký hiệu trúng ĐB", "Ky hieu trung DB");
                    staticContent = staticContent.replaceAll("<table ", "<br><table ");
                    staticContent = staticContent.replaceAll("class=\"firstlast-mb fr\"", "class=\"firstlast-mb fr\" style=\" float: right;text-align: center;width: 50%;  \"");
                    staticContent = staticContent.replaceAll("class=\"firstlast-mb fl\"", "class=\"firstlast-mb f1\" style=\" float: left;text-align: center;width: 50%;  \"");
                    staticContent = staticContent.replace("class=\"kqmb extendable\"", "style=\" float: left;text-align: center; \"");
                    staticContent = staticContent.replaceAll("border=\"0\"", "border=\"1\"");
                    staticContent = staticContent.replaceAll("cellpadding=\"0\"", "cellpadding=\"5\"");
                    staticContent = staticContent.replaceAll("Đầu", "DAU");
                    staticContent = staticContent.replaceAll("Đuôi", "DUOI");
                    staticContent = staticContent.replaceAll("Đặc biệt", "DB");
                    staticContent = staticContent.replaceAll("Giải nhất", "G1");
                    staticContent = staticContent.replace("Giải nhì", "G2");
                    staticContent = staticContent.replace("Giải ba", "G3");
                    staticContent = staticContent.replace("Giải tư", "G4");
                    staticContent = staticContent.replace("Giải năm", "G5");
                    staticContent = staticContent.replace("Giải sáu", "G6");
                    staticContent = staticContent.replace("Giải bảy", "G7");

                    staticContent = staticContent.replace("\"In vé dò\" href=\"https://xoso.me/in-ve-do.html\"", "");
                    Elements trs = kq.getElementsByTag("tr");
                    staticContent = staticContent.replace("In vé dò", "");

//                    for (Element element : trs) {
//                        Article article = new Article();
//                        Element titleSubject = element.getElementsByTag("h3").first();
//                        Element imgSubject = element.getElementsByTag("img").first();
//                        Element linkSubject = element.getElementsByTag("a").first();
//                        Element descrip = element.getElementsByTag("h4").first();
//                        //Parse to model
//                        if (titleSubject != null) {
//                            String title = titleSubject.text();
//                            article.setTitle(title);
//                        }
//                        if (imgSubject != null) {
//                            String src = imgSubject.attr("src");
//                            article.setThumnail(src);
//                        }
//                        if (linkSubject != null) {
//                            String link = linkSubject.attr("href");
//                            article.setUrl(link);
//                        }
//                        if (descrip != null) {
//                            String des = descrip.text();
//                            article.setDecription(des);
//                        }
//                        //Add to list
//                        listArticle.add(article);
//                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return staticContent;
        }

        @Override
        protected void onPostExecute(String staticContent) {
            super.onPostExecute(staticContent);
            webView.loadData(staticContent, "text/html", "utf-8");
            //Setup data recyclerView
            //  articleAdapter = new ArticleAdapter(KQTHActivity.this,articles);
            // recycler.setAdapter(articleAdapter);
        }
    }
}
