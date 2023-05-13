package ru.mirea.boevas.mireaproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ProfileFragment extends Fragment {

    EditText groupEditText;
    EditText numberEditText;
    EditText favoriteMovieEditText;
    Button saveButton;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        SharedPreferences sharedPref = getActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);

        String group = sharedPref.getString("group", "");
        String email = sharedPref.getString("email", "");
        String number = sharedPref.getString("number", "");

        groupEditText = view.findViewById(R.id.groupEditText);
        numberEditText = view.findViewById(R.id.editTextTextEmailAddress2);
        favoriteMovieEditText = view.findViewById(R.id.editTextPhone);

        groupEditText.setText(group);
        numberEditText.setText(email);
        favoriteMovieEditText.setText(number);

        saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Сохраняем данные в SharedPreferences
                SharedPreferences sharedPref = getActivity().getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("group", groupEditText.getText().toString());
                editor.putString("email", numberEditText.getText().toString());
                editor.putString("number", favoriteMovieEditText.getText().toString());
                editor.apply();
            }
        });

        return view;
    }
}
