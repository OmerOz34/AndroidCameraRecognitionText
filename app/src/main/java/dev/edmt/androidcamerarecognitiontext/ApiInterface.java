package dev.edmt.androidcamerarecognitiontext;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("detect")
    Call<Recognition> RecogniseLang(@Query("key") String key, @Query("text") String text);

    @FormUrlEncoded
    @POST("translate")

    Call<Translate> Translate(@Field("key") String key, @Field("text") String text, @Field("lang") String lang);









}
