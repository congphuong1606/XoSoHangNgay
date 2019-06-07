package com.lynkmieu.guujoup.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lynkmieu.guujoup.R;
import com.lynkmieu.guujoup.model.TinhThanh;

import java.util.ArrayList;

public class TinhThanhAdapter extends RecyclerView.Adapter<TinhThanhAdapter.MyViewHolder> {

    private ArrayList<TinhThanh> tinhthanhs;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.name_tinh_thanh);

        }
    }


    public TinhThanhAdapter(ArrayList<TinhThanh> tinhthanhs) {
        this.tinhthanhs = tinhthanhs;
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

    @Override
    public int getItemCount() {
        return tinhthanhs.size();
    }
}