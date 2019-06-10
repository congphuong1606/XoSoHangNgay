package com.lynkmieu.guujoup.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SoMo {

    @SerializedName("stt")
    @Expose
    private String stt;
    @SerializedName("tengiacmo")
    @Expose
    private String tengiacmo;
    @SerializedName("so")
    @Expose
    String so;

    public SoMo() {
    }

    public SoMo(String stt, String tengiacmo, String so) {
        this.stt = stt;
        this.tengiacmo = tengiacmo;
        this.so = so;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getTengiacmo() {
        return tengiacmo;
    }

    public void setTengiacmo(String tengiacmo) {
        this.tengiacmo = tengiacmo;
    }

    public String getSo() {
        return so;
    }

    public void setSo(String so) {
        this.so = so;
    }
}
