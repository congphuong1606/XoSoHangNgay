package com.phuongcong.xosohay.model;

import org.jsoup.select.Evaluator;

public class ItemMenu {
    String title;
    int image;
    String className;

    public ItemMenu(String title, int image, String className) {
        this.title = title;
        this.image = image;
        this.className = className;
    }

    public ItemMenu() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
