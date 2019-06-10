package com.lynkmieu.guujoup.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lynkmieu.guujoup.R;
import com.lynkmieu.guujoup.event.OnClickSoiCau;
import com.lynkmieu.guujoup.model.SoiCau;

import java.util.ArrayList;


public class SoiCauAdapter extends RecyclerView.Adapter<SoiCauAdapter.MyViewHolder> {
    OnClickSoiCau onClickSoiCau;
    Activity activity;
    private ArrayList<SoiCau> soiCaus;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView content;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_title);
            content = (TextView) view.findViewById(R.id.tv_content);
            imageView = (ImageView) view.findViewById(R.id.img_thumbnail);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickSoiCau.click(soiCaus.get(getAdapterPosition()).getLink());
                }
            });

        }
    }


    public SoiCauAdapter(ArrayList<SoiCau> soiCaus, OnClickSoiCau onClickSoiCau, Activity activity) {
        this.soiCaus = soiCaus;
        this.onClickSoiCau = onClickSoiCau;
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.soicau_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SoiCau soiCau = soiCaus.get(position);
        holder.title.setText(soiCau.getTitle());
        holder.content.setText(soiCau.getDescription());
        boolean isMienBac = soiCau.getTitle().contains("Miền Bắc");
        boolean isMienNam = soiCau.getTitle().contains("Miền Nam");
        boolean isMienTrung = soiCau.getTitle().contains("Miền Trung");
        int image = R.drawable.soicaumienbac;
        if (isMienBac) {
            image = R.drawable.soicaumienbac;
        }
        if (isMienNam) {
            image = R.drawable.soicaumiennam;
        }
        if (isMienTrung) {
            image = R.drawable.soicaumientrung;
        }
        try {
            Glide.with(activity)
                    .load(image)
                    .into(holder.imageView);
        } catch (Exception e) {


        }


    }


    @Override
    public int getItemCount() {
        return soiCaus.size();
    }
}