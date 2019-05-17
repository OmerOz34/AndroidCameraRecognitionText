package dev.edmt.androidcamerarecognitiontext;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("detect")
    Call<Recognition> RecogniseLang(@Query("key") String key, @Query("text") String text);


    @GET("translate")
    Call<Translate> Translate(@Query("key") String key, @Query("text") String text, @Query("lang") String lang);









}
