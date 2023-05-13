package ru.mirea.boevas.securesharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.security.keystore.KeyGenParameterSpec;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.security.GeneralSecurityException;
public class MainActivity extends AppCompatActivity {
    KeyGenParameterSpec keyGenParameterSpec	=	MasterKeys.AES256_GCM_SPEC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences secureSharedPreferences;
        try {
            String mainKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec);
            secureSharedPreferences = EncryptedSharedPreferences.create(
                    "secret_shared_prefs",
                    mainKeyAlias,
                    getBaseContext(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
            secureSharedPreferences.edit().putString("secure", "ЛЮБИМЫЙ ПОЭТ").apply();
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }

        // Отображение имени поэта
        String poetName = secureSharedPreferences.getString("secure", "ЛЮБИМЫЙ ПОЭТ");
        TextView poetTextView = findViewById(R.id.poet_text_view);
        poetTextView.setText(poetName);

        // Отображение фотографии или рисунка поэта
        ImageView poetImageView = findViewById(R.id.poet_image_view);
        if (poetName.equals("Пушкин")) {
            poetImageView.setImageResource(R.drawable.ic_launcher_foreground);
        }
    }
}
