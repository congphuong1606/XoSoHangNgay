package com.phuongcong.xosohay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.phuongcong.xosohay.R;
import com.phuongcong.xosohay.activity.MainActivity;
import com.phuongcong.xosohay.model.SoMo;

import java.util.ArrayList;
import java.util.Locale;

public class SomoAdapter extends RecyclerView.Adapter<SomoAdapter.MyViewHolder> {

    private ArrayList<SoMo> soMos;
    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout ln_layout;
        public TextView ten;
        public TextView so;

        public MyViewHolder(View view) {
            super(view);
            ten = (TextView) view.findViewById(R.id.tv_ten_giac_mo);
            so = (TextView) view.findViewById(R.id.tv_so_giac_mo);
            ln_layout = (LinearLayout) view.findViewById(R.id.ln_layout);

        }
    }


    public SomoAdapter(ArrayList<SoMo> soMos, Context abc) {
        this.soMos = soMos;
        context = abc;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.somo_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SoMo soMo = soMos.get(position);
        holder.ten.setText(soMo.getTengiacmo());
        holder.so.setText(soMo.getSo());
        int b = Integer.parseInt(soMo.getStt()) % 2;
        if (b == 1) {
            holder.ln_layout.setBackgroundColor(context.getResources().getColor(R.color.xam));
        }

    }

    @Override
    public int getItemCount() {
        return soMos.size();
    }


    public void filter(String charText, ArrayList<SoMo> listSeach) {
        charText = charText.toLowerCase(Locale.getDefault());
        soMos.clear();
        if (charText.length() == 0) {
            soMos.addAll(listSeach);
        } else {
            for (SoMo l : listSeach) {
                if (l.getTengiacmo().toLowerCase(Locale.getDefault()).contains(charText)) {
                    soMos.add(l);
                }
            }
        }
        notifyDataSetChanged();

    }

}