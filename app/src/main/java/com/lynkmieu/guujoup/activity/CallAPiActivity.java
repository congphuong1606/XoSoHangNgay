package com.lynkmieu.guujoup.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.lynkmieu.guujoup.R;
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

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CallAPiActivity extends AppCompatActivity {

    int i = 1;
    ArrayList<SoMo> listsomo = new ArrayList<>();
    private ApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_api);
        mApiService = ApiUtils.getIapiService();
        new CallAPiActivity.PareseURL().execute(new String[]{getUrl(i)});
    }

    private String getUrl(int i) {
        String siteUrl = "https://xoso.me/so-mo-lo-de-mien-bac-so-mo-giai-mong/" + i + ".html";
        return siteUrl;
    }

    private class PareseURL extends AsyncTask<String, Void, ArrayList<SoMo>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected ArrayList<SoMo> doInBackground(String... params) {
            ArrayList<SoMo> soMos = new ArrayList<>();
            try {
                Document document = Jsoup.connect(params[0]).get();

                Elements lis = document.getElementsByClass("list-dream").get(0).getElementsByTag("li");

                for (Element li : lis) {
                    Elements elements = li.getElementsByTag("strong");
                    String stt = elements.get(0).text();
                    String giacmo = elements.get(1).text();
                    String so = elements.get(2).text();
                    soMos.add(new SoMo(stt, giacmo, so));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return soMos;
        }

        @Override
        protected void onPostExecute(ArrayList<SoMo> s) {
            super.onPostExecute(s);

            if (s != null) {

                listsomo.addAll(s);
                i++;
                if (i <= 60) {

                    new PareseURL().execute(new String[]{getUrl(i)});
                } else {
                    Toast.makeText(CallAPiActivity.this, "dfđgdfg  size" + listsomo.size(), Toast.LENGTH_LONG).show();
                    int t = 2000;
                    int j = 1;
                    for (SoMo xxx : listsomo) {
                        Handler handler = new Handler();
                        int finalJ = j;
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                api(xxx, finalJ);
                            }
                        }, t);
                        j++;
                        t = t + 2000;
                    }
                }
            } else {

            }
        }


        public void addNote(SoMo soMo) {
            SQLiteDatabase db = Database.initDatabase(CallAPiActivity.this, "xosohangngay.db");

            ContentValues values = new ContentValues();
            values.put("tengiacmo", soMo.getTengiacmo());
            values.put("so", soMo.getSo());


            // Trèn một dòng dữ liệu vào bảng.
            db.insert("somo", null, values);


            // Đóng kết nối database.
            db.close();
        }

    }

    private void api(SoMo soMo, int i) {
        mApiService.callApi("newstory", new Story(i + "", soMo.getTengiacmo(), soMo.getSo()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onFail);
    }

    private void onSuccess(Result result) {
        Toast.makeText(CallAPiActivity.this, result.getResult(), Toast.LENGTH_LONG).show();
    }


    private void onFail(Throwable throwable) {
      /*  if(String.valueOf(throwable).equals("com.google.gson.JsonSyntaxException: java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path $")){
            mReadingView.onRequestFail(Constants.LOGIN_FAIL);
        }*/

    }
}
