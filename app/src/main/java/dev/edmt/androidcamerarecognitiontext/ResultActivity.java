package dev.edmt.androidcamerarecognitiontext;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
String text;
TextView twresult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        twresult=findViewById(R.id.tw_translated);
        text=getIntent().getStringExtra("result");
        Log.d("gelen metin",""+text);
        twresult.setText(text.toString());



    }

    public void GoBack(View view) {

    startActivity(new Intent(ResultActivity.this,MainActivity.class));
    finish();
    }


}
