package ru.mirea.boevas.mireaproject;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileOutputStream;

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

        try {
            FileOutputStream outputStream = getActivity().openFileOutput(fileName + fileExtension, Context.MODE_PRIVATE);
            String fileContent = mFileContentEditText.getText().toString();


            byte[] encryptedBytes = encryptXOR(fileContent.getBytes(), 42);

            outputStream.write(encryptedBytes);
            outputStream.close();
            Toast.makeText(requireContext(), "File created successfully", Toast.LENGTH_SHORT).show();
            dismiss();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(requireContext(), "Failed to create file", Toast.LENGTH_SHORT).show();
        }
    }

    private byte[] encryptXOR(byte[] input, int key) {
        byte[] output = new byte[input.length];
        for (int i = 0; i < input.length; i++) {
            output[i] = (byte) (input[i] ^ key);
        }
        return output;
    }

}
