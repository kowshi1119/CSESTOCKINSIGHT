package com.example.csestockinsight;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

// refer: GitHub/Gemini
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton fundamentalButton = findViewById(R.id.btn_fundamental);
        MaterialButton technicalButton = findViewById(R.id.btn_technical);

        fundamentalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FundamentalAnalyzerActivity.class);
                startActivity(intent);
            }
        });

        technicalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TechnicalForecastActivity.class);
                startActivity(intent);
            }
        });
    }
}