package dev.edmt.androidcamerarecognitiontext;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit== null){
            String BASE_URL = "https://translate.yandex.net/api/v1.5/tr.json/";
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient())
                    .build();
            return retrofit;
        }
        return retrofit;

    }//ApiClient.class




}
