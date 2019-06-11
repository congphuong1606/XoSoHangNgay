package com.phuongcong.xosohay.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.EditText;

import com.phuongcong.xosohay.R;
import com.phuongcong.xosohay.adapter.TinhThanhAdapter;
import com.phuongcong.xosohay.event.OnClickTinhThanh;
import com.phuongcong.xosohay.model.TinhThanh;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class KQTHActivity extends AppCompatActivity implements OnClickTinhThanh {
    ArrayList<TinhThanh> TINHTHANHS = new ArrayList<TinhThanh>();
    ArrayList<TinhThanh> listSeach = new ArrayList<TinhThanh>();
    public static final String MY_URL = "https://xoso.me/embedded/kq-mienbac";
    private WebView webView;

    private RecyclerView recyclerView;
    private TinhThanhAdapter mAdapter;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kqth);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        editText = (EditText) findViewById(R.id.edt_search_tinh_thanh);

        mAdapter = new TinhThanhAdapter(TINHTHANHS, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        seach();
        new KQTHActivity.PareseURL().execute(new String[]{"https://xoso.me"});


    }

    private class PareseURL extends AsyncTask<String, Void, ArrayList<TinhThanh>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected ArrayList<TinhThanh> doInBackground(String... params) {
            ArrayList<TinhThanh> tinhthanhs = new ArrayList<>();
            try {
                Document document = Jsoup.connect(params[0]).validateTLSCertificates(false).get();
                String sss = document.text();
                Elements jumbotrons = document.getElementsByClass("col-center").get(0).getElementsByTag("li");
                int i = 0;
                for (Element element : jumbotrons) {
                    if (i != 6 && i != 7 && i != 8 && i != 9 && i != 10 && i != 11) {
                        String link = element.getElementsByTag("a").get(0).absUrl("href");
                        String title = element.getElementsByTag("a").get(0).text();
                        TinhThanh tinhThanh = new TinhThanh(title, link);
                        tinhthanhs.add(tinhThanh);
                    }
                    i++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return tinhthanhs;
        }

        @Override
        protected void onPostExecute(ArrayList<TinhThanh> s) {
            super.onPostExecute(s);

            if (s != null) {
                TINHTHANHS.addAll(s);
                listSeach.addAll(s);
                mAdapter.notifyDataSetChanged();
            }


        }
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

    @Override
    public void click(TinhThanh tinhThanh) {
        Intent intent = new Intent(KQTHActivity.this, CityDetailActivity.class);
        intent.putExtra("link", tinhThanh.getParam());
        startActivity(intent);

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
