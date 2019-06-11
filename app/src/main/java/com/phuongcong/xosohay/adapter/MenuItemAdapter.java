package com.phuongcong.xosohay.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.phuongcong.xosohay.R;
import com.phuongcong.xosohay.activity.DetailArticleActivity;
import com.phuongcong.xosohay.event.OnClickMenuItem;
import com.phuongcong.xosohay.model.Article;
import com.phuongcong.xosohay.model.ItemMenu;

import java.util.ArrayList;

/**
 * Created by LynkMieu on 6/1/2017.
 */

public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.ArticleHolder> {
    private Activity activity;
    private ArrayList<ItemMenu> itemMenus;
    OnClickMenuItem onClickMenuItem;

    public MenuItemAdapter(Activity activity, ArrayList<ItemMenu> itemMenus, OnClickMenuItem onClickMenuItem) {
        this.activity = activity;
        this.itemMenus = itemMenus;
        this.onClickMenuItem = onClickMenuItem;
    }

    @Override
    public ArticleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_layout, parent, false);
        return new ArticleHolder(view);
    }

    @Override
    public void onBindViewHolder(final ArticleHolder holder, int position) {

        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((int) (width * 0.6), ViewGroup.LayoutParams.MATCH_PARENT);
        holder.layoutItem.setLayoutParams(lp);
        final ItemMenu itemMenu = itemMenus.get(position);
        holder.tvTitle.setText(itemMenu.getTitle());
        holder.btnClick.setImageResource(itemMenu.getImage());

    }

    @Override
    public int getItemCount() {
        return itemMenus.size();
    }

    class ArticleHolder extends RecyclerView.ViewHolder {

        private ImageView btnClick;
        private TextView tvTitle;
        private RelativeLayout layoutItem;

        public ArticleHolder(View itemView) {
            super(itemView);
            btnClick = (ImageView) itemView.findViewById(R.id.btn_click);
            layoutItem = (RelativeLayout) itemView.findViewById(R.id.layoutItem);
            tvTitle = (TextView) itemView.findViewById(R.id.tv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickMenuItem.click(itemMenus.get(getAdapterPosition()).getClassName());
                }
            });
        }
    }
}
