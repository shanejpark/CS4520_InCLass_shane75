package com.example.demo_firstproject.practice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.demo_firstproject.R;

public class Practice extends AppCompatActivity {
    private Button logCat, toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        logCat = findViewById(R.id.logCat);
        logCat.setOnClickListener(v ->
                Log.d("demo", "Practice!Practice!!Practice!!!"));

        toast = findViewById(R.id.toast);
        toast.setOnClickListener(v ->
                Toast.makeText(this, "Now push to GitHub and Submit!", Toast.LENGTH_LONG).show());
    }
}