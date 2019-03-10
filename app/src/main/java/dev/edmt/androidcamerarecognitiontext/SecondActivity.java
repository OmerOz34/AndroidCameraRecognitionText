package dev.edmt.androidcamerarecognitiontext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView textView= (TextView) findViewById(R.id.ODUUUUUUUUU);
        Intent intent = getIntent();
        ArrayList<String> list=intent.getStringArrayListExtra("liat");
        textView.setText(list.get(0).toString());
    }
}
