package com.phuongcong.xosohay.model;

public class SoiCau {
    String image;
    String title;
    String description;
    String link;

    public SoiCau(String image, String title, String description, String link) {
        this.image = image;
        this.title = title;
        this.description = description;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public SoiCau() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
