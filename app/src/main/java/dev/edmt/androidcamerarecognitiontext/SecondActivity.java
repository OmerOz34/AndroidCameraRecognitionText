package dev.edmt.androidcamerarecognitiontext;

import android.content.Context;
import android.content.Intent;
import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SecondActivity extends AppCompatActivity {

    private String targetlang ="tr";
    TextView twresult;
    EditText editText;
    Button button;
    ArrayList<String> list;
    ApiInterface apiInterface;
    String text;
    Context context;
    String capturedText ="";
    Translate translate=new Translate();
    FrameLayout frameLayout ;
    FloatingActionButton floatingButton;
    public final String APIKEY="trnsl.1.1.20190201T180527Z.7edab1ce7e8ea3ba.f9ade9de839798c673c125a958b936e3090f4d78";




    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Choose the target language");

        getMenuInflater().inflate(R.menu.example_menu, menu);
    }

    public String getTargetlang() {
        return targetlang;
    }

    public void setTargetlang(String targetlang) {
        this.targetlang = targetlang;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.option1:
                setTargetlang("tr");
                Toast.makeText(this, "Türkçe" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option2:
                setTargetlang("en");
                Toast.makeText(this, "English" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option3:
                setTargetlang("de");
                Toast.makeText(this, "Deutsch" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option4:
                setTargetlang("fr");
                Toast.makeText(this, "Français" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option5:
                setTargetlang("es");
                Toast.makeText(this, "Español" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option6:
                setTargetlang("it");
                Toast.makeText(this, "italiano" , Toast.LENGTH_SHORT).show();
                return true;
            case R.id.option7:
                setTargetlang("nl");
                Toast.makeText(this, "Nederlands" , Toast.LENGTH_SHORT).show();
            default:
                return super.onContextItemSelected(item);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        floatingButton=findViewById(R.id.floating_button);
        frameLayout=findViewById(R.id.layout);
        twresult= findViewById(R.id.tw_result);

        text=getIntent().getStringExtra("string");
        list=getIntent().getStringArrayListExtra("array");
        context=getApplicationContext();

        registerForContextMenu(floatingButton);
        for(int i=0; i<list.size(); i++)   capturedText = " " + capturedText + " " + list.get(i);
        twresult.setText(capturedText);

        Snackbar snackbar = Snackbar.make(frameLayout, "translation language : "+targetlang, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    public void Translate(View view) {

if(!isNetworkConnected()){

    Snackbar snackbar = Snackbar.make(frameLayout, "NO INTERNET CONNECTION", Snackbar.LENGTH_LONG);
    snackbar.show();
}
else{

    apiInterface=ApiClient.getClient().create(ApiInterface.class);

    Call<Translate> call=apiInterface.Translate(APIKEY,twresult.getText().toString(),getTargetlang());
    call.enqueue(new Callback<Translate>() {
        @Override
        public void onResponse(Call<Translate> call, Response<Translate> response) {

            if(response.isSuccessful()){
                assert response.body() != null;
                translate.setText(response.body().getText());
                translate.setCode(response.body().getCode());
                Intent i=new Intent(SecondActivity.this,ResultActivity.class);
                i.putExtra("result",translate.getText().toString());
                startActivity(i);
            }

        }

        @Override
        public void onFailure(Call<Translate> call, Throwable t) {
            Toast.makeText(context, "ERROR" , Toast.LENGTH_SHORT).show();}
    });

}}


        public void TextViewClick(View view) {

            AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
            builder.setCancelable(true);
            LayoutInflater inflater = SecondActivity.this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout. alert_dialog, null);
            builder.setView(dialogView);
            editText=dialogView.findViewById (R.id.dialog_edittext);
            button=dialogView.findViewById(R.id.dialog_button);
            editText.setText(twresult.getText().toString(),TextView.BufferType.EDITABLE);
            final AlertDialog alert = builder.create();
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    twresult.setText(editText.getText().toString());
                    alert.dismiss();
                }
            });
            Objects.requireNonNull(alert.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alert.show();
}



    public void FloatingButton(View view) {






    }
}