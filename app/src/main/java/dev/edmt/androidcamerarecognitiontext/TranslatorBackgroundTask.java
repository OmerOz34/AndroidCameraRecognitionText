package dev.edmt.androidcamerarecognitiontext;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by DoguD on 01/07/2017.
 */

public class TranslatorBackgroundTask extends AsyncTask<String, Void, String> {
    //Declare Context
    Context ctx;
    //Set Context
    TranslatorBackgroundTask(Context ctx){
        this.ctx = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        //String variables
        String textToBeTranslated = params[0];
        String lang = params[1];
        String DetectedLanguage;

        String jsonString;

        try {
            //Set up the translation call URL
            String yandexKey = "trnsl.1.1.20190201T180527Z.7edab1ce7e8ea3ba.f9ade9de839798c673c125a958b936e3090f4d78\n";
            String yandexUrlLang="https://translate.yandex.net/api/v1.5/tr/detect?key=" +yandexKey
                    + "&text=" + textToBeTranslated;

            URL yandexTranslateURL1 = new URL(yandexUrlLang);

            //Set Http Conncection, Input Stream, and Buffered Reader
            HttpURLConnection httpJsonConnection1 = (HttpURLConnection) yandexTranslateURL1.openConnection();
            InputStream inputStream1 = httpJsonConnection1.getInputStream();
            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(inputStream1));

            //Set string builder and insert retrieved JSON result into it
            StringBuilder jsonStringBuilder1 = new StringBuilder();
            while ((jsonString = bufferedReader1.readLine()) != null) {
                jsonStringBuilder1.append(jsonString + "\n");
            }

            //Close and disconnect
            bufferedReader1.close();
            inputStream1.close();
            httpJsonConnection1.disconnect();

            //Making result human readable
            String resultString1 = jsonStringBuilder1.toString().trim();
            //Getting the characters between [ and ]
            resultString1 = resultString1.substring(resultString1.indexOf("<DetectedLang")+1);
            resultString1 = resultString1.substring(0,resultString1.indexOf(">"));
            //Getting the characters between " and "
            resultString1 = resultString1.substring(resultString1.indexOf("lang=\"")+1);

            resultString1 = resultString1.substring(resultString1.indexOf("\"")+1);
            resultString1 = resultString1.substring(0,resultString1.indexOf("\""));

            Log.d("Translation Lang:", resultString1);
            DetectedLanguage = jsonStringBuilder1.toString().trim();


      //      return  DetectedLanguage;
        //    System.out.print(languagePair);

            String languagePair = resultString1 + lang;

           String yandexUrl = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + yandexKey
                    + "&text=" + textToBeTranslated + "&lang=" + languagePair;
            URL yandexTranslateURL = new URL(yandexUrl);

            //Set Http Conncection, Input Stream, and Buffered Reader
            HttpURLConnection httpJsonConnection = (HttpURLConnection) yandexTranslateURL.openConnection();
            InputStream inputStream = httpJsonConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            //Set string builder and insert retrieved JSON result into it
            StringBuilder jsonStringBuilder = new StringBuilder();
            while ((jsonString = bufferedReader.readLine()) != null) {
                jsonStringBuilder.append(jsonString + "\n");
            }

            //Close and disconnect
            bufferedReader.close();
            inputStream.close();
            httpJsonConnection.disconnect();

            //Making result human readable
            String resultString = jsonStringBuilder.toString().trim();
            //Getting the characters between [ and ]
            resultString = resultString.substring(resultString.indexOf('[')+1);
            resultString = resultString.substring(0,resultString.indexOf("]"));
            //Getting the characters between " and "
            resultString = resultString.substring(resultString.indexOf("\"")+1);
            resultString = resultString.substring(0,resultString.indexOf("\""));

            Log.d("Translation Result:", resultString);
            return jsonStringBuilder.toString().trim();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result ) {



    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
