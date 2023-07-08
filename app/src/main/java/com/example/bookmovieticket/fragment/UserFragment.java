package com.example.bookmovieticket.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bookmovieticket.R;
import com.example.bookmovieticket.activity.LoginActivity;
import com.example.bookmovieticket.activity.MainActivity;


public class UserFragment extends Fragment {


    private AppCompatButton btnLogout;
    private TextView txtName, txtEmail;
    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnLogout = view.findViewById(R.id.textView15);
        txtName = view.findViewById(R.id.textView);
        txtEmail = view.findViewById(R.id.textView2);

        String name = ((MainActivity) getActivity()).getUserName();
        txtName.setText(name);
        String email = ((MainActivity) getActivity()).getEmailUser();
        txtEmail.setText(email);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = ((MainActivity) getActivity()).getSharedPreferences("MyPrefs",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                ((MainActivity) getActivity()).finish();
            }
        });
    }
}