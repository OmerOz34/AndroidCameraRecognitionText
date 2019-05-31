package dev.edmt.androidcamerarecognitiontext;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
     TextRecognizer textRecognizer;
    SurfaceView cameraView;
    TextView textView;
    CameraSource cameraSource;
    final int RequestCameraPermissionID = 1001;
    ArrayList<String> list=new ArrayList<>();
    String text="";
    String space=" ";
    BitmapFactory.Options options ;
    private ImageButton button;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == RequestCameraPermissionID) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                try {
                    cameraSource.start(cameraView.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        button.setBackgroundResource(R.drawable.button1);

    }

    @Override
    public void onPause() {
        super.onPause();
        button.setBackgroundResource(R.drawable.button1);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraView =  findViewById(R.id.surface_view);
       // textView = (TextView) findViewById(R.id.text_view);
        button=findViewById(R.id.ButtonView);
         textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();



       // Intent i=new Intent(MainActivity.this,SecondActivity.class);
       // startActivity(i);



        if (!textRecognizer.isOperational()) {
            Log.w("MainActivity", "Detector dependencies are not yet available");
        } else {

            cameraSource = new CameraSource.Builder(getApplicationContext(), textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024)
                    .setRequestedFps(2.0f)
                    .setAutoFocusEnabled(true)
                    .build();
            cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {

                    try {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                                ActivityCompat.requestPermissions(MainActivity.this,
                                        new String[]{Manifest.permission.CAMERA},
                                        RequestCameraPermissionID);
                             return;
                        }
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    cameraSource.stop();
                }
            });





        }
    }





    public void TakePicture(View view){
        button.setBackgroundResource(R.drawable.button2);





        cameraSource.takePicture(null, new CameraSource.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] bytes) {




                Bitmap myBitmap=BitmapFactory.decodeByteArray(bytes, 0, bytes.length,options);
                Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
                final SparseArray<TextBlock> items=textRecognizer.detect(frame);
                if(items.size() != 0)
                {

                    StringBuilder stringBuilder = new StringBuilder();
                    text="";
                    for(int i =0;i<items.size();i++)
                    {
                        TextBlock item = items.valueAt(i);
                        list.add(item.getValue());
                        stringBuilder.append(item.getValue());
                        text=text+item.getValue()+space;
                        stringBuilder.append("\n");
                    }

                }
                else text="Could not recognise any text";
                Intent i=new Intent(MainActivity.this,SecondActivity.class);
                i.putExtra("string",text);
                i.putStringArrayListExtra("array",list);
                startActivity(i);
            }
        });


    }





}
