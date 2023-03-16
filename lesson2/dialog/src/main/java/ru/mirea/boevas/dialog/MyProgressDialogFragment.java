package ru.mirea.boevas.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class MyProgressDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        ProgressBar progressBar = new ProgressBar(getActivity(), null, android.R.attr.progressBarStyle);
        progressBar.setIndeterminate(true);
        progressBar.setPadding(20, 20, 20, 20);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(progressBar);
        builder.setCancelable(false);
        builder.setTitle("Loading...");

        Dialog dialog = builder.create();
        dialog.getWindow().setGravity(Gravity.CENTER);

        return dialog;
    }
}
