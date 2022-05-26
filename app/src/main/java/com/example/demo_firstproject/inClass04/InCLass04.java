package com.example.demo_firstproject.inClass04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.demo_firstproject.R;

import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InCLass04 extends AppCompatActivity {
    private Button generateButton;
    private SeekBar complexityBar;
    private ProgressBar progress;
    private TextView complexityText, minimumText, maximumText, averageText;

    private int n;

    private ExecutorService threadPool;
    private Handler messageQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class04);
        setTitle("Number Generator");


        generateButton = findViewById(R.id.generate_number_button);
        complexityBar = findViewById(R.id.complexity_seekbar);
        complexityText = findViewById(R.id.complexity_text);
        minimumText = findViewById(R.id.minimum_text);
        maximumText = findViewById(R.id.maximum_text);
        averageText = findViewById(R.id.average_text);
        progress = findViewById(R.id.progressBar);

        n = 1;
        complexityBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                n = progress + 1;
                complexityText.setText(String.format(Locale.US, "%d %s", n, n == 1 ? "time" : "times"));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        threadPool = Executors.newFixedThreadPool(2);

        generateButton.setOnClickListener(v -> threadPool.execute(new HeavyWork(n, messageQueue)));

        messageQueue = new Handler(msg -> {
            switch (msg.what) {
                case HeavyWork.STATUS_START:
                    progress.setVisibility(View.VISIBLE);
                    break;
                case HeavyWork.STATUS_END:
                    Bundle endData = msg.getData();
                    progress.setVisibility(View.INVISIBLE);
                    Double minData = endData.getDouble(HeavyWork.MIN);
                    Double maxData = endData.getDouble(HeavyWork.MAX);
                    Double avgData = endData.getDouble(HeavyWork.AVG);
                    minimumText.setText(String.format(Locale.US,
                            "%s %f", "Minimum: ", minData));
                    maximumText.setText(String.format(Locale.US,
                            "%s %f", "Maximum: ", maxData));
                    averageText.setText(String.format(Locale.US,
                            "%s %f", "Average: ", avgData));
                    break;
                case HeavyWork.STATUS_PROGRESS:
                    Bundle receivedData = msg.getData();
                    int donePercent = receivedData.getInt(HeavyWork.KEY_PROGRESS);
                    progress.setProgress(donePercent);
                    break;
            }
            return false;
        });
    }
}