package dev.edmt.androidcamerarecognitiontext;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SecondActivity extends AppCompatActivity {

    EditText editText;
    ArrayList<String> list;
    ApiInterface apiInterface;
    String text;
    String langcode;
    String  transl="";
    Recognition recognition=new Recognition();
    Translate translate=new Translate();
    public final String APIKEY="trnsl.1.1.20190201T180527Z.7edab1ce7e8ea3ba.f9ade9de839798c673c125a958b936e3090f4d78";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editText=(EditText) findViewById(R.id.edittext);
        text=getIntent().getStringExtra("string");
        list=getIntent().getStringArrayListExtra("array");
        for(int i=0; i<list.size(); i++){
            transl = " " + transl + " " + list.get(i);
        }



        editText.setText(transl, TextView.BufferType.EDITABLE);




    }






    void Retro(){

        apiInterface=ApiClient.getClient().create(ApiInterface.class);

        Call<Recognition> call=apiInterface.RecogniseLang("trnsl.1.1.20190201T180527Z.7edab1ce7e8ea3ba.f9ade9de839798c673c125a958b936e3090f4d78",editText.getText().toString());

        call.enqueue(new Callback<Recognition>() {
            @Override
            public void onResponse(@NonNull Call<Recognition> call, @NonNull Response<Recognition> response) {

                Log.d("******vCODE",""+response.body().getCode());
                Log.d("******lang",""+response.body().getLang());
                recognition.setCode(response.body().getCode());
                recognition.setLang(response.body().getLang());

                langcode = recognition.getLang() + "-tr";
            }

            @Override
            public void onFailure(Call<Recognition> call, Throwable t) {

            }
        });


        Call<Translate> call2=apiInterface.Translate(APIKEY,editText.getText().toString(),langcode);
        call2.enqueue(new Callback<Translate>() {
            @Override
            public void onResponse(Call<Translate> call, Response<Translate> response) {

                if(response.isSuccessful()){
                    translate.setText(response.body().getText());
                    translate.setCode(response.body().getCode());
                    Log.d("****222222222222222",""+response.body().getCode());
                    Log.d("******lang2222",""+response.body().getText());

                }


            }

            @Override
            public void onFailure(Call<Translate> call, Throwable t) {

            }
        });

        //editText.setText(translate.getText().get(0), TextView.BufferType.EDITABLE);


    }

    public void Translate(View view) {

        Retro();

    }
}


