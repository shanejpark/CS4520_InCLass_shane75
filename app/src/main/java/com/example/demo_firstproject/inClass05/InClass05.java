package com.example.demo_firstproject.inClass05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.demo_firstproject.R;
import com.squareup.picasso.Picasso;

public class InClass05 extends AppCompatActivity {
    ImageView image, previous, next;
    Button go;
    TextView loadingText;
    ProgressBar loadingProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class05);
        setTitle("Image Search");

        image = findViewById(R.id.loaded_image);
        previous = findViewById(R.id.previous_button);
        next = findViewById(R.id.next_button);

        go = findViewById(R.id.go_button);

        loadingText = findViewById(R.id.loading_text);

        loadingProgress = findViewById(R.id.loading_progress_bar);


    }
}