package ru.mirea.boevas.httpurlconnection;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadWeatherTask extends AsyncTask<String, Void, String> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Добавьте код для обновления интерфейса перед загрузкой погоды
    }

    @Override
    protected String doInBackground(String... urls) {
        try {
            return downloadWeather(urls[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @Override
    protected void onPostExecute(String result) {
        // Обновите интерфейс после загрузки погоды
        try {
            JSONObject responseJson = new JSONObject(result);
            // Обработайте JSON-ответ и обновите соответствующие текстовые поля
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String downloadWeather(String address) throws IOException {
        InputStream inputStream = null;
        String data = "";
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(100000);
            connection.setConnectTimeout(100000);
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(true);
            connection.setUseCaches(false);
            connection.setDoInput(true);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = connection.getInputStream();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                int read = 0;
                while ((read = inputStream.read()) != -1) {
                    bos.write(read);
                }
                bos.close();
                data = bos.toString();
            } else {
                data = connection.getResponseMessage() + ". Error Code: " + responseCode;
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return data;
    }
}
