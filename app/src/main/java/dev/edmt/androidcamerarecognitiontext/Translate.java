package dev.edmt.androidcamerarecognitiontext;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Translate {


    @SerializedName("code")
    @Expose
    private String code;


    @SerializedName("lang")
    @Expose
    private String lang;


    @SerializedName("text")
    @Expose
    private ArrayList<String> text;


    public ArrayList<String> getText() {
        return text;
    }

    public void setText(ArrayList<String> text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }


}
