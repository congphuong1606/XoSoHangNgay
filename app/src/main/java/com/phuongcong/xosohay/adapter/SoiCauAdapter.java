package com.phuongcong.xosohay.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.phuongcong.xosohay.R;
import com.phuongcong.xosohay.event.OnClickSoiCau;
import com.phuongcong.xosohay.model.SoiCau;
import com.phuongcong.xosohay.utils.NetworkUtils;

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
                    if (NetworkUtils.isOnline(activity)) {
                        onClickSoiCau.click(soiCaus.get(getAdapterPosition()).getLink());
                    } else {
                        Toast.makeText(activity, "Kiểm tra kết nối!", Toast.LENGTH_LONG).show();
                    }

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