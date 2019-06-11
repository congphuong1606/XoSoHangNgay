package com.phuongcong.xosohay.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.phuongcong.xosohay.R;
import com.phuongcong.xosohay.model.Article;

public class DetailArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_article);
        Article article = (Article) getIntent().getSerializableExtra("Article");
    }
}
