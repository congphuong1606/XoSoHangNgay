package com.phuongcong.xosohay.model;

public class cityDetail {
    String leftLink;
    String rightLinh;
    String contentHtml;

    public cityDetail() {
    }

    public cityDetail(String leftLink, String rightLinh, String contentHtml) {
        this.leftLink = leftLink;
        this.rightLinh = rightLinh;
        this.contentHtml = contentHtml;
    }

    public String getLeftLink() {
        return leftLink;
    }

    public void setLeftLink(String leftLink) {
        this.leftLink = leftLink;
    }

    public String getRightLinh() {
        return rightLinh;
    }

    public void setRightLinh(String rightLinh) {
        this.rightLinh = rightLinh;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }
}
