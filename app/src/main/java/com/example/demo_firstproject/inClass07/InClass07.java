package com.example.demo_firstproject.inClass07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.demo_firstproject.R;

public class InClass07 extends AppCompatActivity implements Register.IToken {
    private Token token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class07);
        setTitle("Notes");

        if (token == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.InClass07,
                            new start())
                    .addToBackStack(null)
                    .commit();
        }
        else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.InClass07,
                            Notes.newInstance(token.getToken()))
                    .commit();
        }
    }

    @Override
    public void fromFragment(Token token) {
        this.token = token;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.InClass07,
                        Notes.newInstance(token.getToken()))
                .commit();
    }
}