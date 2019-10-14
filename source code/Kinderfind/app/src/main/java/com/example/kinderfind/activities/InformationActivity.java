package com.example.kinderfind.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.kinderfind.R;
import com.example.kinderfind.models.Kindergarten;

public class InformationActivity extends AppCompatActivity {
    public static Kindergarten kindergarten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
    }
}
