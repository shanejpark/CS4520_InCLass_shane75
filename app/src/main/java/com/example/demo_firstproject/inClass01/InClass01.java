package com.example.demo_firstproject.inClass01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_firstproject.R;

import java.text.DecimalFormat;

public class InClass01 extends AppCompatActivity {
    private final String BMI = "Your BMI: ";
    private final String STATUS = "You are ";

    private EditText weightInput, footInput, inchInput;
    private Button calculateButton;
    private TextView resultTextView, statusTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class01);
        setTitle("BMI Calculator");

        weightInput = findViewById(R.id.weight_input);
        footInput = findViewById(R.id.feet_input);
        inchInput = findViewById(R.id.inch_input);

        calculateButton = findViewById(R.id.calculate_button);

        resultTextView = findViewById(R.id.result_text);
        statusTextView = findViewById(R.id.status_text);

        calculateButton.setOnClickListener(v -> {
            String weightString = weightInput.getText().toString();
            String feetString = footInput.getText().toString();
            String inchString = inchInput.getText().toString();
            if (weightString.equals("") || feetString.equals("") || inchString.equals("")) {
                Toast.makeText(this, "Invalid Inputs", Toast.LENGTH_LONG).show();
            }
            else {
                double inch = Double.parseDouble(inchString);
                double weight = Double.parseDouble(weightString);
                double height = (Double.parseDouble(feetString) * 12) + inch;
                if (weight == 0 || height == 0 || inch > 11) {
                    Toast.makeText(this, "Invalid Inputs", Toast.LENGTH_LONG).show();
                }
                else {
                    double bmi = 703 * weight
                            / Math.pow(height, 2);
                    Toast.makeText(this, "BMI Calculated", Toast.LENGTH_LONG).show();

                    DecimalFormat formatter = new DecimalFormat("#0.0");
                    String bmiDisplayText = BMI + formatter.format(bmi);
                    resultTextView.setText(bmiDisplayText);
                    String statusText;
                    if (bmi < 18.5) {
                        statusText = STATUS + "underweight";
                    } else if (bmi < 25) {
                        statusText = STATUS + "at a normal weight";
                    } else if (bmi < 30) {
                        statusText = STATUS + "overweight";
                    } else {
                        statusText = STATUS + "obese";
                    }
                    statusTextView.setText(statusText);
                    statusTextView.setVisibility(View.VISIBLE);
                }
            }
        });
    }


}