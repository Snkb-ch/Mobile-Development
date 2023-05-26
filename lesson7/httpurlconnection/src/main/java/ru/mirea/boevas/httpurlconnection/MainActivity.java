package ru.mirea.boevas.httpurlconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import ru.mirea.boevas.httpurlconnection.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    public void onClick(View view) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkinfo = null;
        if (connectivityManager != null) {
            networkinfo = connectivityManager.getActiveNetworkInfo();
        }
        if (networkinfo != null && networkinfo.isConnected()) {
            new DownloadPageTask().execute("https://ipinfo.io/json");
        } else {
            Toast.makeText(this, "Нет интернета", Toast.LENGTH_SHORT).show();
        }
    }

    private class DownloadPageTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.textView.setText("Загружаем...");
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                return downloadIpInfo(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject responseJson = new JSONObject(result);
                String latitude = responseJson.getString("loc").split(",")[0];
                String longitude = responseJson.getString("loc").split(",")[1];

                new DownloadWeatherTask().execute(latitude, longitude);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private class DownloadWeatherTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.weatherTextView.setText("Загружаем погоду...");
        }

        @Override
        protected String doInBackground(String... coordinates) {
            try {
                String latitude = coordinates[0];
                String longitude = coordinates[1];
                String weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&current_weather=true";
                return downloadWeatherInfo(weatherUrl);
            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject responseJson = new JSONObject(result);
                JSONObject currentWeatherJson = responseJson.getJSONObject("current_weather");
                double temperature = currentWeatherJson.getDouble("temperature");

                // Теперь у вас есть значение температуры
                // Выведите его на экран
                String temperatureString = Double.toString(temperature);
                binding.weatherTextView.setText("Температура: "+ temperatureString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private String downloadIpInfo(String address) throws IOException {
        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(100000);
        connection.setConnectTimeout(100000);
        connection.setRequestMethod("GET");
        connection.setInstanceFollowRedirects(true);
        connection.setUseCaches(false);
        connection.setDoInput(true);

        int responseCode = connection.getResponseCode();
        StringBuilder data = new StringBuilder();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
            reader.close();
        } else {
            data.append(connection.getResponseMessage()).append(". Error Code: ").append(responseCode);
        }
        connection.disconnect();
        return data.toString();
    }

    private String downloadWeatherInfo(String address) throws IOException {
        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(100000);
        connection.setConnectTimeout(100000);
        connection.setRequestMethod("GET");
        connection.setInstanceFollowRedirects(true);
        connection.setUseCaches(false);
        connection.setDoInput(true);

        int responseCode = connection.getResponseCode();
        StringBuilder data = new StringBuilder();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
            reader.close();
        } else {
            data.append(connection.getResponseMessage()).append(". Error Code: ").append(responseCode);
        }
        connection.disconnect();
        return data.toString();
    }
}