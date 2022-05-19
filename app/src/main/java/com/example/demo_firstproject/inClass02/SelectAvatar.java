package com.example.demo_firstproject.inClass02;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_firstproject.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SelectAvatar extends AppCompatActivity {
    ImageView f1, f2, f3, m1, m2, m3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_avatar);
        setTitle(R.string.select_avatar);

        f1 = findViewById(R.id.avatar_f_1);
        f2 = findViewById(R.id.avatar_f_2);
        f3 = findViewById(R.id.avatar_f_3);
        m1 = findViewById(R.id.avatar_m_1);
        m2 = findViewById(R.id.avatar_m_2);
        m3 = findViewById(R.id.avatar_m_3);

        f1.setOnClickListener(v -> {
            setResult(RESULT_OK, new Intent().putExtra("To InClass02", R.drawable.avatar_f_1));
            finish();
        });

        f2.setOnClickListener(v -> {
            setResult(RESULT_OK, new Intent().putExtra("To InClass02", R.drawable.avatar_f_2));
            finish();
        });

        f3.setOnClickListener(v -> {
            setResult(RESULT_OK, new Intent().putExtra("To InClass02", R.drawable.avatar_f_3));
            finish();
        });

        m1.setOnClickListener(v -> {
            setResult(RESULT_OK, new Intent().putExtra("To InClass02", R.drawable.avatar_m_1));
            finish();
        });

        m2.setOnClickListener(v -> {
            setResult(RESULT_OK, new Intent().putExtra("To InClass02", R.drawable.avatar_m_2));
            finish();
        });

        m3.setOnClickListener(v -> {
            setResult(RESULT_OK, new Intent().putExtra("To InClass03", R.drawable.avatar_m_3));
            finish();
        });
    }
}