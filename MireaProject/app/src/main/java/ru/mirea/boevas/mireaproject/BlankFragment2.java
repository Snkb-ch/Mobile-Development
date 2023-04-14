package ru.mirea.boevas.mireaproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import ru.mirea.boevas.mireaproject.MyWorker;
import ru.mirea.boevas.mireaproject.R;

public class BlankFragment2 extends Fragment {

    public BlankFragment2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank2, container, false);

        // Создайте экземпляр класса WorkManager
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .setRequiresCharging(true)
                .build();
        WorkRequest uploadWorkRequest =
                new OneTimeWorkRequest.Builder(MyWorker.class)
                        .setConstraints(constraints)
                        .build();
        WorkManager
                .getInstance(getContext())
                .enqueue(uploadWorkRequest);

        return view;
    }
}