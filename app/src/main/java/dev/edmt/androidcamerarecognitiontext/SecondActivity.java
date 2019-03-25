package dev.edmt.androidcamerarecognitiontext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editText=(EditText) findViewById(R.id.edittext);
        Intent intent = getIntent();
        String s=getIntent().getStringExtra("string");
        editText.setText(s, TextView.BufferType.EDITABLE);

    }
}
