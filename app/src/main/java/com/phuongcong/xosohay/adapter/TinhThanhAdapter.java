package com.phuongcong.xosohay.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.phuongcong.xosohay.R;
import com.phuongcong.xosohay.event.OnClickTinhThanh;
import com.phuongcong.xosohay.model.TinhThanh;
import com.phuongcong.xosohay.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.Locale;

public class TinhThanhAdapter extends RecyclerView.Adapter<TinhThanhAdapter.MyViewHolder> {
    private final Activity activity;

    OnClickTinhThanh onClickTinhThanh;
    private ArrayList<TinhThanh> tinhthanhs;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.name_tinh_thanh);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (NetworkUtils.isOnline(activity)) {
                        onClickTinhThanh.click(tinhthanhs.get(getAdapterPosition()));
                    } else {
                        Toast.makeText(activity, "Kiểm tra kết nối!", Toast.LENGTH_LONG).show();
                    }

                }
            });

        }
    }


    public TinhThanhAdapter(ArrayList<TinhThanh> tinhthanhs, OnClickTinhThanh onClickTinhThanh, Activity activity) {
        this.tinhthanhs = tinhthanhs;
        this.onClickTinhThanh=onClickTinhThanh;
        this.activity=activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tinhthanh_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TinhThanh tinhThanh = tinhthanhs.get(position);
        holder.title.setText(tinhThanh.getTen());

    }

    public void filter(String charText, ArrayList<TinhThanh> listSeach) {
        charText = charText.toLowerCase(Locale.getDefault());
        tinhthanhs.clear();
        if (charText.length() == 0) {
            tinhthanhs.addAll(listSeach);
        } else {
            for (TinhThanh l : listSeach) {
                if (l.getTen().toLowerCase(Locale.getDefault()).contains(charText)) {
                    tinhthanhs.add(l);
                }
            }
        }
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return tinhthanhs.size();
    }
}