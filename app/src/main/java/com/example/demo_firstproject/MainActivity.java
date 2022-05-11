package com.example.demo_firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String TAG = "demo";
    Button logCat, toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logCat = findViewById(R.id.logCat);
        toast = findViewById(R.id.toast);
    }

    public void logPractice(View view) {
        Log.d(TAG, "Practice!Practice!!Practice!!!");
    }

    public void toast(View view) {
        Toast.makeText(this, "Now push to GitHub and Submit!", Toast.LENGTH_LONG).show();
    }


}