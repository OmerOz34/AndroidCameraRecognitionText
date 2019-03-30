package dev.edmt.androidcamerarecognitiontext;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SecondActivity extends AppCompatActivity {

    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView textView= (TextView) findViewById(R.id.ODUUUUUUUUU);
        TextView translatedTextView = (TextView) findViewById(R.id.translatedText);
        Intent intent = getIntent();
        String s=getIntent().getStringExtra("string");
        textView.setText(s);

        String translateTo ="-tr";
        String translated =   Translate(s, translateTo);

        translatedTextView.setText(translated);

    }

    public String Translate(String textToBeTranslated, String translateTo){
        TranslatorBackgroundTask translatorBackgroundTask= new TranslatorBackgroundTask(context);
        String translationResult = null; // Returns the translated text as a String
        try {
            translationResult = translatorBackgroundTask.execute(textToBeTranslated, translateTo).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
            e.getCause();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("Translation Result",translationResult); // Logs the result in Android Monitor

        return translationResult;
    }
}
