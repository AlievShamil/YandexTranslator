package com.example.devcom.yandextranslator;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Translator extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        String textToBeTranslated = params[0];
        String languagePair = params[1];

        String jsonString;

        try {
            String yandexKey = "trnsl.1.1.20171124T192351Z.aaa9eb270884720a.e7b996d75073cf3d501c72d5afdac404b0c827c1";
            String yandexUrl = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=" + yandexKey
                    + "&text=" + textToBeTranslated + "&lang=" + languagePair;
            URL yaTranslateURL = new URL(yandexUrl);
            HttpURLConnection httpJsonConnection = (HttpURLConnection) yaTranslateURL.openConnection();
            InputStream inputStream = httpJsonConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder jsonStringBuilder = new StringBuilder();

            while ((jsonString = bufferedReader.readLine()) != null) {
                jsonStringBuilder.append(jsonString).append("\n");
            }
            bufferedReader.close();
            inputStream.close();
            httpJsonConnection.disconnect();

            String resultString = jsonStringBuilder.toString().trim();
            resultString = resultString.substring(resultString.indexOf('[')+1);
            resultString = resultString.substring(0,resultString.indexOf("]"));
            resultString = resultString.substring(resultString.indexOf("\"")+1);
            resultString = resultString.substring(0,resultString.indexOf("\""));

            return resultString;

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
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
