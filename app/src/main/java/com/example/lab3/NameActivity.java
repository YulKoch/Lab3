package com.example.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NameActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "com.example.lab3.NAME";
    private TextView welcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        welcomeTextView = findViewById(R.id.textViewWelcome);
        Button dntCallButton = findViewById(R.id.buttonDontCallMe);
        Button thankYouButton = findViewById(R.id.buttonThankYou);

        Intent intent = getIntent();
        String name = intent.getStringExtra(EXTRA_NAME);
        welcomeTextView.setText(String.format("Welcome %s!", name));


        dntCallButton.setOnClickListener(v -> {
            setResult(0);
        });

        thankYouButton.setOnClickListener(v -> {
            setResult(1);
            finish();
        });
    }
}
