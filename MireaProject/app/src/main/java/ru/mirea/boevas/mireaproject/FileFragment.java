package ru.mirea.boevas.mireaproject;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class FileFragment extends DialogFragment {

    private EditText mFileNameEditText;
    private EditText mFileContentEditText;
    private Button mSaveButton;
    private Button mCancelButton;

    public FileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_file, container, false);

        mFileNameEditText = view.findViewById(R.id.file_name_edit_text);
        mFileContentEditText = view.findViewById(R.id.file_content_edit_text);
        mSaveButton = view.findViewById(R.id.save_button);
        mCancelButton = view.findViewById(R.id.cancel_button);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRecord();
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return view;
    }

    private void saveRecord() {
        String fileName = mFileNameEditText.getText().toString();
        String fileExtension = ".txt";

        File file = new File(getActivity().getExternalFilesDir(null), fileName + fileExtension);
        try {
            boolean result = file.createNewFile();
            if (result) {
                String fileContent = mFileContentEditText.getText().toString();
                // Шифрование файла (например, AES-128)
                String encryptedFileContent = encrypt(fileContent);
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(encryptedFileContent.getBytes());
                fileOutputStream.close();
                Toast.makeText(requireContext(), "File created and encrypted successfully", Toast.LENGTH_SHORT).show();
                dismiss();
            } else {
                Toast.makeText(requireContext(), "Failed to create file", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Функция шифрования
    private String encrypt(String fileContent) {
        // Реализация шифрования (например, AES-128)
        return "encrypted " + fileContent;
    }
}
