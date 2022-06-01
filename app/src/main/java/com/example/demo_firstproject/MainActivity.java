package com.example.demo_firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.demo_firstproject.inClass02.InClass02;
import com.example.demo_firstproject.practice.Practice;

public class MainActivity extends AppCompatActivity {
    private Button practice, InClass01, InClass02, InClass03, InClass04, InClass05, InClass06;

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

        InClass02 = findViewById(R.id.hw2);
        InClass02.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, com.example.demo_firstproject.inClass02.InClass02.class)));

        InClass03 = findViewById(R.id.hw3);
        InClass03.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, com.example.demo_firstproject.inClass03.InClass03.class)));

        InClass04 = findViewById(R.id.hw4);
        InClass04.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, com.example.demo_firstproject.inClass04.InCLass04.class)));

        InClass05 = findViewById(R.id.hw5);
        InClass05.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, com.example.demo_firstproject.inClass05.InClass05.class)));

        InClass06 = findViewById(R.id.hw6);
        InClass06.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, com.example.demo_firstproject.inClass06.InClass06.class)));
    }
}