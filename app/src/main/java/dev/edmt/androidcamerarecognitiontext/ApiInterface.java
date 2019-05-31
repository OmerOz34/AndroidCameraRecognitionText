package dev.edmt.androidcamerarecognitiontext;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("translate")
    Call<Translate> Translate(@Field("key") String key, @Field("text") String text, @Field("lang") String lang);
}
