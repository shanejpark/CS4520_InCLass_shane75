package com.example.demo_firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.demo_firstproject.practice.Practice;

public class MainActivity extends AppCompatActivity {
    private Button practice, InClass01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        practice = findViewById(R.id.hw0);
        practice.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, Practice.class)));

        InClass01 = findViewById(R.id.hw1);
        InClass01.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, com.example.demo_firstproject.inClass01.InClass01.class)));

    }
}