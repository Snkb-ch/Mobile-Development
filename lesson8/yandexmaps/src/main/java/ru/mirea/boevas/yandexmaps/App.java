package ru.mirea.boevas.yandexmaps;

import android.app.Application;

import com.yandex.mapkit.MapKitFactory;

public class App extends Application {
    private final String MAPKIT_API_KEY = "4cbd92ec-e6c1-47a5-a036-a08e30f24e35";
    @Override
    public void onCreate() {
        super.onCreate();
        // Set the api key before calling initialize on MapKitFactory.
        MapKitFactory.setApiKey("4cbd92ec-e6c1-47a5-a036-a08e30f24e35");
    }
}