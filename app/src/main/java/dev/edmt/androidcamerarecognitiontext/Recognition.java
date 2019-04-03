package dev.edmt.androidcamerarecognitiontext;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recognition {

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("lang")
    @Expose
    private String lang;


    public int getCode() { return code; }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
