package ru.mirea.boevas.mireaproject.ui.slideshow;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.mirea.boevas.mireaproject.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment implements SensorEventListener {

    private FragmentSlideshowBinding binding;
    private SensorManager sensorManager;
    private Sensor heightSensor;
    private TextView textbar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        textbar = binding.textbar;

        // Получить экземпляр SensorManager
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        // Получить экземпляр датчика высоты
        heightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Зарегистрировать SensorEventListener для датчика высоты
        sensorManager.registerListener(this, heightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();

        // Отменить регистрацию SensorEventListener
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
            float height = SensorManager.getAltitude(SensorManager.PRESSURE_STANDARD_ATMOSPHERE, event.values[0]);
            textbar.setText(String.format("%.2f м", height));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Не требуется
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        sensorManager.unregisterListener(this);
        binding = null;
    }
}
